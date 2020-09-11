package org.johnny.blogsserver.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.entity.system.Menu;
import org.johnny.blogscommon.entity.system.MenuEntity;
import org.johnny.blogscommon.service.system.MenuService;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.requestvo.system.MenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.MenuResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * MenuController
 *
 * @author johnny
 * @create 2020-07-10 下午2:17
 **/
@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;


    @PostMapping("/add")
    public ResultVo add(@RequestBody MenuReqVo menuReqVo) {
        log.info("【MenuReqVo： {} 】", menuReqVo);
        //可以通过 拦截所有的请求 放入 vo中 操作人
        menuReqVo.setOperator("admin");
        menuService.addMenu(menuReqVo);
        return ResultVoUtil.success();
    }

    @PostMapping("/edit")
    public ResultVo edit(@RequestBody MenuReqVo menuReqVo) {
        log.info("【MenuReqVo： {} 】", menuReqVo);
        menuReqVo.setOperator("admin");
        menuService.editMenu(menuReqVo);
        return ResultVoUtil.success();
    }

    @PostMapping("/delete/{id}")
    public  ResultVo delete(@PathVariable("id") Long id){
        log.info("delete menu id : {}", id);
        menuService.deleteMenu(id);
        return  ResultVoUtil.success();
    }



    @GetMapping("/list")
    public ResultVo list(@RequestParam("all") Integer all) {
        List<MenuResultVo> menuResultVoList;
        if(all == 1){
            menuResultVoList = menuService.findAllMenuList(true);
        }else{
            menuResultVoList = menuService.findAllMenuList(false);
        }
        log.info("【menuResultVoList : {}】", menuResultVoList);
        return ResultVoUtil.success(menuResultVoList);
    }


}