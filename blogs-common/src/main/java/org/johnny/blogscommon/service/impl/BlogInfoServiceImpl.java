package org.johnny.blogscommon.service.impl;

import com.google.gson.Gson;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.constants.RedisConstankey;
import org.johnny.blogscommon.converter.BlogInfoConverter;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.entity.blog.BlogInfoEsEntity;
import org.johnny.blogscommon.entity.blog.QBlogInfo;
import org.johnny.blogscommon.entity.blog.QBlogType;
import org.johnny.blogscommon.entity.thumb.UserThumbRelation;
import org.johnny.blogscommon.form.BlogInfoForm;
import org.johnny.blogscommon.repository.blog.BlogInfoEsEntityRepository;
import org.johnny.blogscommon.repository.blog.BlogInfoRepository;
import org.johnny.blogscommon.repository.thumb.UserThumbRelationRepository;
import org.johnny.blogscommon.service.BlogInfoService;
import org.johnny.blogscommon.service.BlogTypeService;
import org.johnny.blogscommon.utils.DateUtils;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.vo.blog.Anchor;
import org.johnny.blogscommon.vo.blog.ArchiveBlogVo;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * bloginfo service impl
 *
 * @author johnny
 * @create 2019-11-23 下午7:06
 **/
@Service
@Slf4j
public class BlogInfoServiceImpl implements BlogInfoService {

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private BlogInfoRepository blogInfoRepository;

    /**
     * 为了让 blogs-server项目 不依赖
     */
    @Autowired(required = false)
    private BlogInfoEsEntityRepository blogInfoEsEntityRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private UserThumbRelationRepository userThumbRelationRepository;

    @Override
    public Page<BlogInfoVo> findPageForPhone(Pageable pageable, BlogInfoForm blogInfoForm) {
        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;

        QBlogType qBlogType = QBlogType.blogType;

        JPAQuery jpaQuery = queryFactory.select(qBlogInfo, qBlogType.typeName)
                .from(qBlogInfo)
                .leftJoin(qBlogType)
                .on(qBlogInfo.blogTypeId.eq(qBlogType.id))
                .orderBy(qBlogInfo.createTime.desc());

        System.out.println(pageable.getPageNumber());
        jpaQuery
                .offset(pageable.getPageNumber() * 5)
                .limit(5);

        QueryResults<Tuple> queryResults = jpaQuery.fetchResults();

        List<BlogInfoVo> blogInfoList = new ArrayList<>();
        queryResults.getResults().stream().forEach(tuple -> {
            blogInfoList.add(BlogInfoConverter.INSTANCE.domain2vo(tuple.get(0, BlogInfo.class)).setBlogTypeName(tuple.get(1, String.class)));

        });
        return new PageImpl<>(blogInfoList, pageable, queryResults.getTotal());
    }

    /**
     * 根据order 进行排序 查询Top10 blogInfo 数据
     *
     * @param order : clickCount / thumbCount
     * @return ：List<BlogInfoVo>
     */
    @Override
    public List<BlogInfoVo> queryTopTenBlogInfos(String order) {

        List<BlogInfoVo> blogInfoVos;

        String blogTopTenJson = stringRedisTemplate.opsForValue().get(RedisConstankey.BLOG_TOP_TEN_REDIS_KEY);
        if (StringUtils.isEmpty(blogTopTenJson)) {
            blogInfoVos = new ArrayList<>();
            QBlogInfo qBlogInfo = QBlogInfo.blogInfo;
            JPAQuery<Tuple> jpaQuery = queryFactory.select(qBlogInfo.id, qBlogInfo.blogTitle, qBlogInfo.thumbCount)
                    .from(qBlogInfo)
                    .offset(0)
                    .limit(10);

            if (QBlogInfo.blogInfo.clickCount.toString().equalsIgnoreCase(order)) {
                jpaQuery.orderBy(qBlogInfo.clickCount.desc());
            }
            System.out.println(QBlogInfo.blogInfo.thumbCount.toString());
            if (QBlogInfo.blogInfo.thumbCount.toString().equalsIgnoreCase(order)) {
                jpaQuery.orderBy(qBlogInfo.thumbCount.desc());
            }

            jpaQuery.fetch().forEach(tuple -> {
                BlogInfo blogInfo = new BlogInfo();
                blogInfo.setId(tuple.get(0, Long.class));
                blogInfo.setBlogTitle(tuple.get(1, String.class));
                blogInfo.setThumbCount(tuple.get(2, Long.class));
                blogInfoVos.add(BlogInfoConverter.INSTANCE.domain2vo(blogInfo));
            });
            stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_TOP_TEN_REDIS_KEY, GsonUtils.toJsonStr(blogInfoVos));
        } else {
            Gson gson = new Gson();
            blogInfoVos = gson.fromJson(blogTopTenJson, List.class);
        }
        return blogInfoVos;
    }

    private List<BlogInfoVo> tupleToBlogInfoVo(List<Tuple> tuples, List<BlogInfoVo> blogInfoVos) {
        tuples.stream().forEach(tuple -> {
            BlogInfo blogInfo = new BlogInfo();
            blogInfo.setId(tuple.get(0, Long.class));
            blogInfo.setCreateDate(tuple.get(1, Date.class));
            blogInfo.setBlogTitle(tuple.get(2, String.class));
            blogInfo.setCreateTime(tuple.get(3, Date.class));
            blogInfoVos.add(BlogInfoConverter.INSTANCE.domain2vo(blogInfo));
        });
        return blogInfoVos;
    }

    /**
     * 查询归档的 博客
     *
     * @return
     */
    @Override
    public List<ArchiveBlogVo> queryArchiveBlog() {

        List<BlogInfoVo> blogInfoVos = new ArrayList<>();

        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;
        List<Tuple> tuples = queryFactory.select(qBlogInfo.id, qBlogInfo.createDate, qBlogInfo.blogTitle, qBlogInfo.createTime)
                .from(qBlogInfo)
                .fetch();

        blogInfoVos = tupleToBlogInfoVo(tuples, blogInfoVos);

        //设置一下 createMonth ，供下面分组
        blogInfoVos.forEach(blogInfoVo -> {
            blogInfoVo.setCreateMonth(DateUtils.formatDate(DateUtils.parse(blogInfoVo.getCreateTime(), "yyyy-MM-dd"), "yyyy-MM"));
        });

        //排序然后分组，安装createMonth
        Map<String, List<BlogInfoVo>> map = blogInfoVos.stream().sorted(Comparator.comparing(BlogInfoVo::getCreateTime).reversed()).collect(Collectors.groupingBy(BlogInfoVo::getCreateMonth));

        List<Date> dates = new ArrayList<>();
        for (String s : map.keySet()) {
            dates.add(DateUtils.parse(s, "yyyy-MM"));
        }
        //对map 的key 既月份进行排序
        List<String> datesStr = DateUtils.compareDate(dates);

        LinkedHashMap<String, List<BlogInfoVo>> archiveBlogs = new LinkedHashMap();

        for (String s : datesStr) {
            archiveBlogs.put(s, map.get(s));
        }

        //转换一下 前端方便展示
        List<ArchiveBlogVo> archiveBlogVos = new ArrayList<>();
        ArchiveBlogVo archiveBlogVo;
        for (String archiveDate : archiveBlogs.keySet()) {
            archiveBlogVo = new ArchiveBlogVo();
            archiveBlogVo.setArchiveDate(archiveDate);
            archiveBlogVo.setBlogInfoVoList(archiveBlogs.get(archiveDate));
            archiveBlogVos.add(archiveBlogVo);
        }
        return archiveBlogVos;
    }

    @Override
    public List<BlogInfoVo> queryBLogForSearch(String blogTitle) {

        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;

        List<Tuple> fetch = queryFactory.select(qBlogInfo.id, qBlogInfo.blogTitle)
                .from(qBlogInfo)
                .where(qBlogInfo.blogTitle.like("%" + blogTitle + "%"))
                .fetch();
        List<BlogInfoVo> blogInfoVos = new ArrayList<>();
        if (fetch != null) {
            fetch.stream().forEach(tuple -> {
                Long id = tuple.get(0, Long.class);
                String name = tuple.get(1, String.class);

                BlogInfoVo blogInfoVo = new BlogInfoVo();
                blogInfoVo.setId(id);
                blogInfoVo.setBlogTitle(name);
                blogInfoVos.add(blogInfoVo);
            });

        }

        return blogInfoVos;
    }


    /**
     * 查询 blogInfo 信息  带分页
     *
     * @param pageable : pageable
     * @return : page
     */
    @Override
    public Page<BlogInfoVo> findPage(Pageable pageable, BlogInfoForm blogInfoForm) {

        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;

        QBlogType qBlogType = QBlogType.blogType;


        JPAQuery jpaQuery = queryFactory.select(
                qBlogInfo.id,
                qBlogInfo.blogTitle,
                qBlogInfo.blogImageUrl,
                qBlogInfo.blogShortContent,
                qBlogInfo.createDate,
                qBlogType.typeName,
                qBlogType.blogTypeAnchor,
                qBlogInfo.createUser,
                qBlogInfo.thumbCount,
                qBlogInfo.updateTime)

                .from(qBlogInfo)
                .leftJoin(qBlogType)
                .on(qBlogInfo.blogTypeId.eq(qBlogType.id))
                .orderBy(qBlogInfo.createTime.desc());


        if (!StringUtils.isEmpty(blogInfoForm.getIds()) && blogInfoForm.getIds().size() > 1) {
//            jpaQuery
//                    .offset(pageable.getPageNumber() * pageable.getPageSize())
//                    .limit(pageable.getPageSize());

            jpaQuery.where(qBlogInfo.id.in(blogInfoForm.getIds()));
        }

        if (!StringUtils.isEmpty(blogInfoForm.getIds()) && blogInfoForm.getIds().size() == 1) {
            jpaQuery.where(qBlogInfo.id.in(blogInfoForm.getIds()));
            jpaQuery
                    .offset(pageable.getPageNumber() * pageable.getPageSize())
                    .limit(pageable.getPageSize());
            //表示是选择一个的
        } else {
            jpaQuery
                    .offset(pageable.getPageNumber() * pageable.getPageSize())
                    .limit(pageable.getPageSize());
        }

        QueryResults<Tuple> queryResults = jpaQuery.fetchResults();

        List<BlogInfoVo> blogInfoList = new ArrayList<>();


        queryResults.getResults().stream().forEach(tuple -> {

            BlogInfo blogInfo = new BlogInfo();
            blogInfo.setId(tuple.get(0, Long.class));
            blogInfo.setBlogTitle(tuple.get(1, String.class));
            blogInfo.setBlogImageUrl(tuple.get(2, String.class));
            blogInfo.setBlogShortContent(tuple.get(3, String.class));
            blogInfo.setCreateDate(tuple.get(4, Date.class));
            blogInfo.setCreateUser(tuple.get(7, String.class));
            blogInfo.setThumbCount(tuple.get(8, Long.class));
            blogInfo.setUpdateTime(tuple.get(9, Date.class));

            BlogInfoVo blogInfoVo = BlogInfoConverter.INSTANCE.domain2vo(blogInfo)
                    .setBlogTypeName(tuple.get(5, String.class))
                    .setBlogTypeAnchor(tuple.get(6, String.class));

            if (blogInfo.getUpdateTime() != null) {
                blogInfoVo.setUpdateTime(DateUtils.formatDate(blogInfo.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            }
            blogInfoList.add(blogInfoVo);

//            blogInfoList.add(BlogInfoConverter.INSTANCE.domain2vo(
//                    tuple.get(0, Long.class)
//                    tuple.get(0, BlogInfo.class))
//
//                    .setBlogTypeName(tuple.get(1, String.class))
//                    .setBlogTypeAnchor(String.valueOf(tuple.get(2, String.class))));
        });
        if (blogInfoForm.getIds().size() == 1) {
            return new PageImpl<>(blogInfoList, pageable, 1);
        } else {
            return new PageImpl<>(blogInfoList, pageable, queryResults.getTotal());
        }

    }

    /**
     * 根据 Id 查询博客信息
     *
     * @param id : id
     * @return : 博客信息
     */
    @Override
    public BlogInfoVo queryById(Long id) {
        BlogInfoVo blogInfoVo = null;
        //1.从Redis中查询
        String blogInfoJsonStr = stringRedisTemplate.opsForValue().get(RedisConstankey.BLOG_INFO_PREFIX_KEY + id);
        Gson gson = new Gson();
        BlogInfo redisBlogInfo = gson.fromJson(blogInfoJsonStr, BlogInfo.class);
        if (redisBlogInfo != null) {
            log.info("【get redisBlogInfo： {}】", redisBlogInfo.getBlogTitle());
            List<Anchor> list = gson.fromJson(redisBlogInfo.getAnchorJson(), List.class);
            blogInfoVo = BlogInfoConverter.INSTANCE.domain2vo(redisBlogInfo);
            blogInfoVo.setAnchors(list);
        } else {
            QBlogInfo qBlogInfo = QBlogInfo.blogInfo;
            QBlogType qBlogType = QBlogType.blogType;
            Tuple tuple = queryFactory.select(qBlogInfo, qBlogType.typeName, qBlogType.blogTypeAnchor)
                    .from(qBlogInfo)
                    .leftJoin(qBlogType)
                    .on(qBlogInfo.blogTypeId.eq(qBlogType.id))
                    .where(qBlogInfo.id.eq(id))
                    .fetchOne();
            BlogInfo blogInfo = tuple.get(0, BlogInfo.class);

            if (blogInfo != null) {
                log.info("【set redisBlogInfo： {}】", redisBlogInfo);
                blogInfo.setBlogTypeName(tuple.get(1, String.class));
                blogInfo.setBlogTypeAnchor(tuple.get(2, String.class));
                stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_INFO_PREFIX_KEY + blogInfo.getId(), gson.toJson(blogInfo));
            }
            List<Anchor> list = gson.fromJson(blogInfo.getAnchorJson(), List.class);
            blogInfoVo = BlogInfoConverter.INSTANCE.domain2vo(tuple.get(0, BlogInfo.class))
                    .setBlogTypeName(tuple.get(1, String.class))
                    .setBlogTypeAnchor(tuple.get(2, String.class));
            blogInfoVo.setAnchors(list);
        }
        //查询 上一条  下一条信息
        fillPreviousNextBlogInfo(id, blogInfoVo);

        return blogInfoVo;
    }


    private void fillPreviousNextBlogInfo(Long id, BlogInfoVo blogInfoVo) {
        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;
        Tuple tuple = queryFactory.select(qBlogInfo.id, qBlogInfo.blogTitle)
                .from(qBlogInfo)
                .where(qBlogInfo.id.lt(id))
                .orderBy(qBlogInfo.id.desc())
                .limit(1).fetchOne();

        if (tuple != null) {
            blogInfoVo.setPreviousBlogId(tuple.get(0, Long.class));
            blogInfoVo.setPreviousBlogTitle(tuple.get(1, String.class));
        } else {
            blogInfoVo.setPreviousBlogId(0L);
        }

        Tuple tuple2 = queryFactory.select(qBlogInfo.id, qBlogInfo.blogTitle)
                .from(qBlogInfo)
                .where(qBlogInfo.id.gt(id))
                .orderBy(qBlogInfo.id.asc())
                .limit(1).fetchOne();

        if (tuple2 != null) {
            blogInfoVo.setNextBlogId(tuple2.get(0, Long.class));
            blogInfoVo.setNextBlogTitle(tuple2.get(1, String.class));
        } else {
            blogInfoVo.setNextBlogId(0L);
        }

    }

    @Override
    public List<BlogInfoVo> queryByTypeId(Integer typeId) {
        List<BlogInfoVo> list = new ArrayList<>();
        QBlogInfo qBlogInfo = QBlogInfo.blogInfo;
        List<Tuple> tuples = queryFactory.select(qBlogInfo.id, qBlogInfo.createDate, qBlogInfo.blogTitle, qBlogInfo.createTime)
                .from(qBlogInfo)
                .where(qBlogInfo.blogTypeId.eq(typeId))
                .fetch();
        list = tupleToBlogInfoVo(tuples, list);

        return list;
    }

    @Override
    public Page<BlogInfoVo> findPageByIds(Pageable pageable, List<Long> ids) {
        List<BlogInfoVo> blogInfoList = new ArrayList<>();
        ids.stream().forEach(id -> {
            blogInfoList.add(queryById(id));
        });

        return null;
    }

    /**
     * 保存 博客信息类
     *
     * @param blogInfoVo
     */
    @Override
    public void addBlogInfo(BlogInfoVo blogInfoVo) {
        Gson gson = new Gson();
        String blogContentHtml = blogInfoVo.getBlogContent();
        //解析锚点
        Document doc = Jsoup.parse(blogContentHtml);
        List<Anchor> anchorList = new ArrayList<>();
        Elements elements = doc.select("h3");
        for (Element element : elements) {
            Anchor anchor = new Anchor();
            anchor.setAnchorName(element.text());
            anchor.setAnchorId(element.select("a").attr("id"));
            anchorList.add(anchor);
        }
        BlogInfo blogInfo = BlogInfoConverter.INSTANCE.vo2domain(blogInfoVo);
        //保存锚点
        blogInfo.setAnchorJson(gson.toJson(anchorList));
        blogInfo.setClickCount(0L);
        blogInfo.setThumbCount(0L);
        blogInfoRepository.save(blogInfo);

        saveToEs(blogInfo);
        refreshRedis();
    }

    //TODO 这里可以写成异步 ，用线程去执行 并且不是按照这种删除 重置方式
    private void refreshRedis() {

        //刷新归档
        stringRedisTemplate.delete(RedisConstankey.ARCHIVGE_BLOG_KEY);
        List<ArchiveBlogVo> archiveBlogVos = queryArchiveBlog();
        stringRedisTemplate.opsForValue().set(RedisConstankey.ARCHIVGE_BLOG_KEY, GsonUtils.toJsonStr(archiveBlogVos));

        //刷新分类
        stringRedisTemplate.delete(RedisConstankey.BLOG_TYPE_REDIS_KEY);
        List<BlogTypeVo> blogInfoVos = blogTypeService.findAllBlogTypeVoList();
        stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_TYPE_REDIS_KEY, GsonUtils.toJsonStr(blogInfoVos));

    }

    @Override
    public void refreshEs(Long id) {
        BlogInfoVo blogInfoVo = queryById(id);
        BlogInfo blogInfo = BlogInfoConverter.INSTANCE.vo2domain(blogInfoVo);
        BlogInfoEsEntity blogInfoEsEntity = new BlogInfoEsEntity();
        blogInfoEsEntity.setBlogInfoId(blogInfo.getId());
        blogInfoEsEntity.setBlogTitle(blogInfo.getBlogTitle());
        blogInfoEsEntity.setId(UUID.randomUUID().toString());
        blogInfoEsEntityRepository.save(blogInfoEsEntity);
    }

    /**
     * 点击 + 1
     *
     * @param id
     */
    @Override
    public void addClick(Long id) {
        BlogInfo blogInfo = blogInfoRepository.findById(id).orElse(null);
        synchronized (this) {
            if (blogInfo != null) {
                blogInfo.setClickCount(blogInfo.getClickCount() + 1);
                blogInfoRepository.save(blogInfo);
            }
        }
    }

    /**
     * 点赞数 + 1
     *
     * @param id
     */
    @Override
    public void addThumbClick(Long id, Long userId) {
        BlogInfo blogInfo = blogInfoRepository.findById(id).get();
        synchronized (this) {
            blogInfo.setThumbCount(blogInfo.getThumbCount() + 1);
            blogInfoRepository.save(blogInfo);
        }
        //存入 缓存
        stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_INFO_PREFIX_KEY + id, GsonUtils.toJsonStr(blogInfo));

        //保存 用户和 点赞得博客 关系
        UserThumbRelation exist = userThumbRelationRepository.findByUserIdAndBlogInfoId(userId, id);
        if (exist == null) {
            UserThumbRelation userThumbRelation = new UserThumbRelation();
            userThumbRelation.setUserId(userId);
            userThumbRelation.setBlogInfoId(id);
            userThumbRelationRepository.save(userThumbRelation);
        }
    }

    @Override
    public void clearThumbClick(Long id, Long userId) {
        BlogInfo blogInfo = blogInfoRepository.findById(id).get();
        synchronized (this) {
            blogInfo.setThumbCount(blogInfo.getThumbCount() - 1);
            blogInfoRepository.save(blogInfo);
        }
        //存入 缓存
        stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_INFO_PREFIX_KEY + id, GsonUtils.toJsonStr(blogInfo));

        //删除 用户和 点赞得博客 关系
        UserThumbRelation userThumbRelation = userThumbRelationRepository.findByUserIdAndBlogInfoId(userId, id);
        if (userThumbRelation != null) {
            userThumbRelationRepository.deleteById(userThumbRelation.getId());
        }
    }

    @Override
    public void refreshEsAll() {
        List<BlogInfo> blogInfos = blogInfoRepository.findAll();
        blogInfoEsEntityRepository.deleteAll();
        blogInfos.stream().forEach(blogInfo -> {
            saveToEs(blogInfo);
        });
    }


    private void saveToEs(BlogInfo blogInfo) {
        BlogInfoEsEntity blogInfoEsEntity = new BlogInfoEsEntity();
        blogInfoEsEntity.setBlogInfoId(blogInfo.getId());
        blogInfoEsEntity.setBlogTitle(blogInfo.getBlogTitle());
        blogInfoEsEntity.setId(UUID.randomUUID().toString());
        blogInfoEsEntityRepository.save(blogInfoEsEntity);
    }
}