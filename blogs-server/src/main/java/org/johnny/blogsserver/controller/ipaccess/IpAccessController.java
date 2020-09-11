package org.johnny.blogsserver.controller.ipaccess;

import javafx.scene.paint.Stop;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogsauth.properties.JwtProperties;
import org.johnny.blogsauth.utils.SecurityUtils;
import org.johnny.blogscommon.entity.ipaccess.City;
import org.johnny.blogscommon.repository.ipaccess.CityRepository;
import org.johnny.blogscommon.service.system.UserService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.ipaccess.IpAccess;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.johnny.blogsserver.job.IpAccessJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户 Controller
 *
 * @author johnny
 * @create 2020-07-10 下午4:08
 **/
@RestController
@Slf4j
@RequestMapping("/ipAccess")
public class IpAccessController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private IpAccessJob ipAccessJob;

    @GetMapping("/getIpAccess")
    public ResultVo getIpAccess() {

        //Object value = redisTemplate.opsForValue().get("无锡");
//        Integer intValue =  Integer.valueOf((String)value);
//        System.out.println("city 无锡:" + Long.valueOf(intValue));
//
//        IpAccess ipAccess = new IpAccess();
//        ipAccess.setName("无锡");
//        ipAccess.setValue(2000L);
//
//        IpAccess ipAccess2 = new IpAccess();
//        ipAccess2.setName("北京");
//        ipAccess2.setValue(200L);
//
//        List<IpAccess> list = new ArrayList<>();
//        list.add(ipAccess);
//        list.add(ipAccess2);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        List<IpAccess> list = new ArrayList<>();
//        List<String> cityNameList = cityRepository.findAll().stream().map(City::getName).collect(Collectors.toList());
//        cityNameList.forEach(cityName -> {
//            String count  = redisTemplate.opsForValue().get(cityName);
//            IpAccess ipAccess = new IpAccess();
//            ipAccess.setName(cityName);
//            ipAccess.setValue(Long.valueOf(count));
//            list.add(ipAccess);
//        });
//        stopWatch.stop();
        List<IpAccess> list = IpAccessJob.ipAccessList;
        if(CollectionUtils.isEmpty(list)){
            ipAccessJob.calcIpAccess();
           list = IpAccessJob.ipAccessList;
        }
        System.out.println(stopWatch.getTotalTimeSeconds());
        log.info("【ipAccess list : {} 】", list);
        //Map<String,String> map = new HashMap<>();
        //map.put("无锡",String.valueOf(intValue));
        return ResultVoUtil.success(list);
    }



}