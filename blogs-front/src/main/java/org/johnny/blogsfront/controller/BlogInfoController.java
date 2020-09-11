package org.johnny.blogsfront.controller;

import com.google.gson.Gson;

import org.johnny.blogscommon.constants.RedisConstankey;
import org.johnny.blogscommon.form.BlogInfoForm;
import org.johnny.blogscommon.service.BlogInfoEsService;
import org.johnny.blogscommon.service.BlogInfoService;
import org.johnny.blogscommon.utils.DateUtils;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.blog.ArchiveBlogVo;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * blog Controller
 *
 * @author johnny
 * @create 2019-11-23 下午6:59
 **/
@RestController
@RequestMapping("/blogInfo")
public class BlogInfoController {

    @Autowired
    private BlogInfoService blogInfoService;



    @Autowired
    private BlogInfoEsService blogInfoEsService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/queryCount")
    @CrossOrigin
    public ResultVo queryCount() {
        return ResultVoUtil.success(DateUtils.discrepancyMonthCount());
    }

    @RequestMapping("/queryArchiveBlog")
    @CrossOrigin
    public ResultVo queryArchiveBlog() {

        List<ArchiveBlogVo> archiveBlogVos;
        String archiveBlogJson = stringRedisTemplate.opsForValue().get(RedisConstankey.ARCHIVGE_BLOG_KEY);
        if (StringUtils.isEmpty(archiveBlogJson)) {
            //redis中没有就从数据库中查询
            archiveBlogVos = blogInfoService.queryArchiveBlog();
            stringRedisTemplate.opsForValue().set(RedisConstankey.ARCHIVGE_BLOG_KEY, GsonUtils.toJsonStr(archiveBlogVos));
        } else {
            Gson gson = new Gson();
            archiveBlogVos = gson.fromJson(archiveBlogJson, List.class);
        }
        return ResultVoUtil.success(archiveBlogVos);
    }


    /**
     * 根据 order进行查询 top10 blogInfo
     *
     * @param order : 排序字段
     * @return : list blogInfovo
     */
    @RequestMapping("/queryTopTenBlogInfos")
    @CrossOrigin
    public ResultVo queryTopTenBlogInfos(@RequestParam("order") String order) {
        List<BlogInfoVo> blogInfoVos = blogInfoService.queryTopTenBlogInfos(order);
        return ResultVoUtil.success(blogInfoVos);
    }


    @PostMapping("/pageForPhone")
    @CrossOrigin
    public ResultVo<Page<BlogInfoVo>> pageForPhone(@RequestBody BlogInfoForm blogInfoForm) {
        Page<BlogInfoVo> page = blogInfoService.findPageForPhone(PageUtil.initPage(blogInfoForm), blogInfoForm);
        return ResultVoUtil.success(page);
    }


    /**
     * 查询blogInfo信息
     *
     * @return : ResultVo
     */
    @PostMapping("/page")
    @CrossOrigin
    public ResultVo<Page<BlogInfoVo>> page(@RequestBody BlogInfoForm blogInfoForm) {
        Page<BlogInfoVo> page = blogInfoService.findPage(PageUtil.initPage(blogInfoForm), blogInfoForm);
        return ResultVoUtil.success(page);
    }


    /**
     * 查询blogInfo信息
     *
     * @return : ResultVo
     */
    @GetMapping("/{id}")
    @CrossOrigin
    public ResultVo<BlogInfoVo> queryById(@PathVariable("id") Long id) {
        BlogInfoVo blogInfoVo = blogInfoService.queryById(id);
        return ResultVoUtil.success(blogInfoVo);

    }

    @GetMapping("/save")
    @CrossOrigin
    public ResultVo<Page<BlogInfoVo>> saveStudy() {
        return null;
    }




    @CrossOrigin
    @GetMapping("/searchBlogEs")
    public ResultVo searchBlogEs(@RequestParam String blogTitle) {
        return ResultVoUtil.success(blogInfoEsService.queryBlogFromEs(blogTitle));
    }


    @CrossOrigin
    @PostMapping("/saveBlogInfo")
    public ResultVo saveBlogInfo(@RequestBody BlogInfoVo blogInfoVo) {
        blogInfoService.addBlogInfo(blogInfoVo);
        return null;
    }


    @GetMapping("/refreshEs/{id}")
    public ResultVo refreshEs(@PathVariable Long id) {
        blogInfoService.refreshEs(id);
        return ResultVoUtil.success();
    }

    @GetMapping("/refreshEs")
    public ResultVo refreshEsAll() {
        blogInfoService.refreshEsAll();
        return ResultVoUtil.success();
    }

    /**
     * 点击
     *
     * @param id
     * @return
     */
    @GetMapping("/addClick/{id}")
    @CrossOrigin
    public ResultVo addClick(@PathVariable Long id) {
        blogInfoService.addClick(id);
        return ResultVoUtil.success();
    }


    @GetMapping("/addThumbClick/{id}")
    @CrossOrigin
    public ResultVo addThumbClick(@PathVariable Long id) {
        blogInfoService.addThumbClick(id);
        return ResultVoUtil.success();
    }

    @GetMapping("/clearThumbClick/{id}")
    @CrossOrigin
    public ResultVo clearThumbClick(@PathVariable Long id) {
        blogInfoService.clearThumbClick(id);
        return ResultVoUtil.success();
    }







//    @GetMapping("/getAuthUrl")
//    @CrossOrigin
//    public ResultVo getAuthUrl() {
//        String url = "http://graph.qq.com/oauth2.0/show?which=Login&display=pc&client_id=101583210&response_type=code&redirect_uri=http%3A%2F%2Fwww.askajohnny.com%2Fblogs%2Fauth%2Fqq&state=0bb793c8-59de-437f-979f-668137ce5f43";
//
//        String authUrl = MySocialFailHandler.authUrl;
//        return ResultVo.success(authUrl);
//    }


}