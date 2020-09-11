package org.johnny.blogsfront.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2019-06-10 下午2:03
 **/
@RestController
public class BlogsListController {

    @CrossOrigin(origins = "*")
    @RequestMapping("/blogsList")
    public List<Map<String,Object>> blogsList(){

        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> map = new HashMap<>();
//        map.put("title","Java设计模式");
//        map.put("imageUrl","/images/zd03.jpg");
//
//        map.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");
//
//
//        Map<String,Object> map2 = new HashMap<>();
//        map2.put("title","JavaWeb Servlet");
//        map2.put("imageUrl","/images/zd01.jpg");
//
//        map2.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");
//
//
//        Map<String,Object> map3 = new HashMap<>();
//        map3.put("title","SpringBoot2.0探究");
//        map3.put("imageUrl","https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png");
//
//        map3.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");

        map.put("id","1");
        map.put("title","Java设计模式");
        map.put("imageUrl","/static/zd03.jpg");
        map.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");
        map.put("createDate","2019-10-19");
        map.put("blogType","Java");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("id","2");
        map2.put("title","JavaWeb Servlet");
        map2.put("imageUrl","/static/zd01.jpg");
        map2.put("createDate","2019-10-10");
        map2.put("blogType","Vue");

        map2.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");


        Map<String,Object> map3 = new HashMap<>();
        map.put("id","3");
        map3.put("title","SpringBoot2.0探究");
        map3.put("imageUrl","https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png");

        map3.put("content","SpringBoot2.0深入探究 主要讲解了如何从SpringFramewrok一步步 转换到SpringBoot的啥是Springboot和书上理解的不同，我认为Springboot是一个优秀的快速搭建框架，他通过maven继承方式添加依赖来整合很多第三方工具");
        map3.put("createDate","2019-09-21");
        map3.put("blogType","数据库");

        list.add(map);
        list.add(map2);
        list.add(map3);
        map.put("id","4");
        list.add(map);
        map2.put("id","5");
        list.add(map2);

        list.add(map3);
        list.add(map);
        list.add(map2);
        list.add(map3);


        return list;
    }
}