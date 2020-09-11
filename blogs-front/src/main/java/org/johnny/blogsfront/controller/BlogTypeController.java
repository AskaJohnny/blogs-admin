package org.johnny.blogsfront.controller;


import org.johnny.blogscommon.service.BlogTypeService;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * blog Controller
 *
 * @author johnny
 * @create 2019-11-23 下午8:29
 **/
@RestController
@RequestMapping("/blogType")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;


    /**
     * 查询BlogTypeVo信息
     * @return : ResultVo
     */
    @GetMapping
    @CrossOrigin
    public ResultVo<List<BlogTypeVo>> list(){

        List<BlogTypeVo> list = blogTypeService.findList();
        return ResultVoUtil.success(list);
    }






}