package org.johnny.blogscommon.service.impl;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.constants.RedisConstankey;
import org.johnny.blogscommon.converter.BlogTypeConverter;
import org.johnny.blogscommon.repository.blog.BlogTypeRepository;
import org.johnny.blogscommon.service.BlogInfoService;
import org.johnny.blogscommon.service.BlogTypeService;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnny
 * @create 2019-11-23 下午8:30
 **/
@Service
@Slf4j
public class BlogTypeServiceImpl implements BlogTypeService {


    @Autowired
    private BlogTypeRepository blogTypeRepository;

    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 查询 类型以及类型下面的所有的blog
     * 后面考虑放到redis种 再说吧
     *
     * @return: List<BlogTypeVo>
     */
    @Override
    public List<BlogTypeVo> findList() {
        List<BlogTypeVo> list;
        if (stringRedisTemplate.hasKey(RedisConstankey.BLOG_TYPE_REDIS_KEY)) {
            String blogTypeListJson = stringRedisTemplate.opsForValue().get(RedisConstankey.BLOG_TYPE_REDIS_KEY);
            list = GsonUtils.fromJsonToList(blogTypeListJson);
        } else {
            //查询 所有  vo 类型list
            list = findAllBlogTypeVoList();
            stringRedisTemplate.opsForValue().set(RedisConstankey.BLOG_TYPE_REDIS_KEY, GsonUtils.toJsonStr(list));
        }
        return list;
    }


    @Override
    public List<BlogTypeVo> findAllBlogTypeVoList() {
        List<BlogTypeVo> list = new ArrayList<>();
        blogTypeRepository.findAll().stream().forEach(blogType -> {
            List<BlogInfoVo> blogInfoVoList = blogInfoService.queryByTypeId(blogType.getId());
            list.add(BlogTypeConverter.INSTANCE.domain2vo(blogType).setBlogInfoVoList(blogInfoVoList));
        });
        return list;
    }
}