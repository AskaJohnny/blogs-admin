package org.johnny.blogsfront.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.entity.ipaccess.IpAccessInfo;
import org.johnny.blogscommon.repository.ipaccess.IpAccessCountRepository;
import org.johnny.blogscommon.repository.ipaccess.IpAccessInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author johnny
 * @create 2020-08-15 下午1:56
 **/
@Component
@Order(value = 1)
@Slf4j
public class IpQueue implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

    }

//
//    public static final LinkedBlockingQueue<IpAccessInfo> linkedBlockingQueue = new LinkedBlockingQueue(10000);
//
//    @Autowired
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
//
//    @Autowired
//    private IpAccessInfoRepository ipAccessInfoRepository;
//
//    @Autowired
//    private IpAccessCountRepository ipAccessCountRepository;
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        ipAccessCountRepository.findAll().forEach(ipAccessCount -> {
//            if (!redisTemplate.hasKey(ipAccessCount.getCity())) {
//                redisTemplate.opsForValue().set(ipAccessCount.getCity(), String.valueOf(ipAccessCount.getCount()));
//            } else {
//                log.info("【存在： {} 】", ipAccessCount.getCity());
//            }
//        });
//        log.info("【服务启动  --------------  监听 队列 IpQueue 】");
//        IpAccessThread ipAccessThread = new IpAccessThread(linkedBlockingQueue, ipAccessInfoRepository, redisTemplate);
//        threadPoolTaskExecutor.submit(ipAccessThread);
//    }
//
//    static class IpAccessThread implements Runnable {
//
//        private LinkedBlockingQueue<IpAccessInfo> linkedBlockingQueue;
//
//        private IpAccessInfoRepository ipAccessInfoRepository;
//
//        private RedisTemplate redisTemplate;
//
//        public IpAccessThread(LinkedBlockingQueue<IpAccessInfo> linkedBlockingQueue, IpAccessInfoRepository ipAccessInfoRepository, RedisTemplate redisTemplate) {
//            this.linkedBlockingQueue = linkedBlockingQueue;
//            this.ipAccessInfoRepository = ipAccessInfoRepository;
//            this.redisTemplate = redisTemplate;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    System.out.println("开始获取 : ");
//                    IpAccessInfo accessInfo = linkedBlockingQueue.take();
//                    System.out.println("监听到 : " + accessInfo);
//                    //江苏省无锡市
//                    if (accessInfo.getCity().contains("省") && accessInfo.getCity().contains("市")) {
//                        String city = accessInfo.getCity();
//                        city = city.substring(city.indexOf("省") + 1, city.indexOf("市"));
//                        if (redisTemplate.hasKey(city)) {
//                            redisTemplate.opsForValue().increment(city);
//                        }
//                    } else {
//                        log.error("【异常 地理位置 {} 】", accessInfo.getCity());
//                    }
//                    ipAccessInfoRepository.save(accessInfo);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    log.info("【监听器 休息60秒】");
//                    Thread.sleep(60000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}