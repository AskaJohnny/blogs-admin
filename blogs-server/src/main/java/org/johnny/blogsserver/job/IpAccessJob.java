package org.johnny.blogsserver.job;

import org.johnny.blogscommon.entity.ipaccess.City;
import org.johnny.blogscommon.repository.ipaccess.CityRepository;
import org.johnny.blogscommon.vo.ipaccess.IpAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author johnny
 * @create 2020-08-14 下午6:05
 **/

@Component
public class IpAccessJob {

    public static List<IpAccess> ipAccessList = new ArrayList<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CityRepository cityRepository;

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void calcIpAccess(){

        ipAccessList.clear();
        List<String> cityNameList = cityRepository.findAll().stream().map(City::getName).collect(Collectors.toList());
        cityNameList.forEach(cityName -> {
            String count  = redisTemplate.opsForValue().get(cityName);
            IpAccess ipAccess = new IpAccess();
            ipAccess.setName(cityName);
            ipAccess.setValue(Long.valueOf(count));
            ipAccessList.add(ipAccess);
        });
    }


}