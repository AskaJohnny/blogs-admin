package org.johnny.blogscommon.service.impl;


import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.johnny.blogscommon.converter.BlogLeaveWordConverter;
import org.johnny.blogscommon.converter.BlogUserInfoConverter;
import org.johnny.blogscommon.entity.blog.BlogLeaveWord;
import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.johnny.blogscommon.entity.blog.QBlogLeaveWord;
import org.johnny.blogscommon.entity.blog.QBlogUserInfo;
import org.johnny.blogscommon.repository.blog.BlogLeaveWordRepository;
import org.johnny.blogscommon.service.BlogLeaveWordService;
import org.johnny.blogscommon.utils.DateUtils;
import org.johnny.blogscommon.vo.blog.BlogUserInfoVo;
import org.johnny.blogscommon.vo.leaveword.BlogLeaveWordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author johnny
 * @create 2019-12-19 下午5:11
 **/
@Service
public class BlogLeaveWordServiceImpl implements BlogLeaveWordService {


    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BlogLeaveWordRepository blogLeaveWordRepository;
    /**
     * 分页查询
     *
     * @param pageable
     * @param blogId
     * @return
     */
    @Override
    public Page<BlogLeaveWordVo> findPage(Pageable pageable, Long blogId) {

        List<BlogLeaveWordVo> leaveWordVoList = new ArrayList<>();
        QBlogLeaveWord qBlogLeaveWord = QBlogLeaveWord.blogLeaveWord;
        QBlogUserInfo qBlogUserInfo = QBlogUserInfo.blogUserInfo;


        JPAQuery jpaQuery = queryFactory.select(qBlogLeaveWord, qBlogUserInfo)
                .from(qBlogLeaveWord)
                .innerJoin(qBlogUserInfo)
                .on(qBlogLeaveWord.blogUserId.eq(qBlogUserInfo.id))
                .orderBy(qBlogLeaveWord.createTime.desc());
            jpaQuery.offset(pageable.getPageSize() * pageable.getPageNumber());
            jpaQuery.limit(pageable.getPageSize());
        if (!StringUtils.isEmpty(blogId)) {
            //查询的是某个博客下面的留言
            jpaQuery.where(qBlogLeaveWord.blogInfoId.eq(blogId));
        } else {
            //查询的是留言板的
            jpaQuery.where(qBlogLeaveWord.blogInfoId.isNull());
        }
        QueryResults<Tuple> queryResults = jpaQuery.fetchResults();
        queryResults.getResults().stream().forEach(tuple -> {
            BlogLeaveWord blogLeaveWord = tuple.get(0, BlogLeaveWord.class);
            //String publishTime = DateUtils.publishTime(blogLeaveWord.getCreateTime());
            BlogUserInfo blogUserInfo = tuple.get(1, BlogUserInfo.class);
            BlogUserInfoVo blogUserInfoVo = BlogUserInfoConverter.INSTANCE.domain2vo(blogUserInfo);
            String publishTime = DateUtils.formatDate(blogLeaveWord.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
            leaveWordVoList.add(BlogLeaveWordConverter.INSTANCE.domain2vo(blogLeaveWord).setBlogUserInfoVo(blogUserInfoVo).setPublishTime(publishTime));
        });
        return new PageImpl<>(leaveWordVoList, pageable, queryResults.getTotal());
    }

    @Override
    public void save(BlogLeaveWordVo blogLeaveWordVo) {
        BlogLeaveWord blogLeaveWord = BlogLeaveWordConverter.INSTANCE.vo2domain(blogLeaveWordVo);
        blogLeaveWord.setCreateTime(new Date());
        blogLeaveWordRepository.save(blogLeaveWord);
    }
}