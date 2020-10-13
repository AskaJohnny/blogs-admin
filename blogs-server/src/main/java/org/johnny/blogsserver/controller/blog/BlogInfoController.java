package org.johnny.blogsserver.controller.blog;

import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.form.BlogInfoForm;
import org.johnny.blogscommon.repository.blog.BlogInfoRepository;
import org.johnny.blogscommon.service.BlogInfoService;
import org.johnny.blogscommon.service.BlogTypeService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 文章的 controller
 *
 * @author johnny
 * @create 2020-08-19 下午12:25
 **/
@RestController
@RequestMapping("/blog")
public class BlogInfoController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BlogInfoRepository blogInfoRepository;


    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Value("${blog.frontUrl}")
    private String blogFrontUrl;


//    /**
//     * 查询blogInfo信息
//     *
//     * @return : ResultVo
//     */
//    @PostMapping("/page")
//    @CrossOrigin
//    public ResultVo<Page<BlogInfoVo>> page(@RequestBody BlogInfoForm blogInfoForm) {
//        //Page<BlogInfoVo> page = blogInfoService.findPage(PageUtil.initPage(blogInfoForm), blogInfoForm);
//        ResultVo result =   restTemplate.postForObject("http://localhost:8000/blogs/blogInfo/page",blogInfoForm,ResultVo.class);
//        System.out.println(result);
//        ResultVo resultVo = ResultVoUtil.success(result.getData());
//        return resultVo;
//    }

    /**
     * 查询blogInfo信息
     *
     * @return : ResultVo
     */
    @PostMapping("/page")
    @CrossOrigin
    public ResultVo<Page<BlogInfoVo>> page(@RequestBody BlogInfoForm blogInfoForm) {
        blogInfoForm.setPageNumber(blogInfoForm.getPageNumber() - 1);
        Page<BlogInfoVo> page = blogInfoService.findPage(PageUtil.initPage(blogInfoForm), blogInfoForm);
        return ResultVoUtil.success(page);
    }


//    /**
//     * 查询BlogTypeVo信息
//     * @return : ResultVo
//     */
//    @GetMapping("/type")
//    @CrossOrigin
//    public ResultVo<Page<BlogTypeVo>> list(){
//        ResultVo result =  restTemplate.getForObject("http://localhost:8000/blogs/blogType",ResultVo.class);
//        ResultVo resultVo = ResultVoUtil.success(result.getData());
//        return resultVo;
//    }


    /**
     * 查询BlogTypeVo信息
     *
     * @return : ResultVo
     */
    @GetMapping("/type")
    @CrossOrigin
    public ResultVo<List<BlogTypeVo>> list() {
        List<BlogTypeVo> list = blogTypeService.findList();
        return ResultVoUtil.success(list);
    }


    @CrossOrigin
    @PostMapping("/saveBlogInfo")
    public ResultVo saveBlogInfo(@RequestBody BlogInfoVo blogInfoVo) {
        //blogInfoService.addBlogInfo(blogInfoVo);
        restTemplate.postForObject(blogFrontUrl, blogInfoVo, String.class);
        return ResultVoUtil.success();
    }

//    /**
//     * 查询BlogTypeVo信息
//     * @return : ResultVo
//     */
//    @CrossOrigin
//    @PostMapping("/saveBlogInfo")
//    public ResultVo saveBlogInfo(@RequestBody BlogInfoVo blogInfoVo) {
//        restTemplate.postForObject("http://localhost:8000/blogs/blogInfo/saveBlogInfo",blogInfoVo,String.class);
//        return  ResultVoUtil.success();
//    }

    /**
     * 查询BlogTypeVo信息
     *
     * @return : ResultVo
     */
    @GetMapping("/{id}")
    @CrossOrigin
    public ResultVo getBlogInfoById(@PathVariable(name = "id") Long id) {
        BlogInfo blogInfo = blogInfoRepository.findById(id).orElse(null);
        return ResultVoUtil.success(blogInfo);
    }


}