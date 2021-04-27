package org.johnny.blogscommon.service;


import org.johnny.blogscommon.form.BlogInfoForm;
import org.johnny.blogscommon.vo.blog.ArchiveBlogVo;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * blog service
 *
 * @author johnny
 * @create 2019-11-23 下午7:05
 **/
public interface BlogInfoService {


    Page<BlogInfoVo> findPage(Pageable pageable, BlogInfoForm blogInfoForm);

    BlogInfoVo queryById(Long id);

    List<BlogInfoVo> queryByTypeId(Integer id);

    Page<BlogInfoVo> findPageByIds(Pageable pageable, List<Long> ids);

    void addBlogInfo(BlogInfoVo blogInfoVo);

    void refreshEs(Long id);

    void addClick(Long id);

    void addThumbClick(Long id,Long userId);

    void clearThumbClick(Long id,Long userId);

    void refreshEsAll();

    Page<BlogInfoVo> findPageForPhone(Pageable pageable, BlogInfoForm blogInfoForm);

    List<BlogInfoVo> queryTopTenBlogInfos(String order);

    List<ArchiveBlogVo> queryArchiveBlog();

    List<BlogInfoVo> queryBLogForSearch(String blogTitle);
}