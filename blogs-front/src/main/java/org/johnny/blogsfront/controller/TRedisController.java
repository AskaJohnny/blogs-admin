package org.johnny.blogsfront.controller;

import com.google.gson.Gson;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.repository.blog.BlogInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author johnny
 * @create 2020-01-20 上午9:59
 **/
@RestController
@RequestMapping("/redis")
public class TRedisController {

    private static final String REDIS_KEY_PREFIX = "blogInfo_";

    @Autowired
    private BlogInfoRepository blogInfoRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    public void setBlogInfo() {
        Gson gson = new Gson();
        BlogInfo blogInfo = blogInfoRepository.findById(40L).orElse(null);
        if (blogInfo != null) {
            stringRedisTemplate.opsForValue().set(REDIS_KEY_PREFIX + blogInfo.getId(), gson.toJson(blogInfo));
            return;
        }
    }


    @RequestMapping("/get")
    public BlogInfo getBlogInfo() {
        String blogInfoJson = stringRedisTemplate.opsForValue().get(REDIS_KEY_PREFIX + "40");
        Gson gson = new Gson();
        BlogInfo blogInfo = gson.fromJson(blogInfoJson, BlogInfo.class);
        return blogInfo;
    }
}