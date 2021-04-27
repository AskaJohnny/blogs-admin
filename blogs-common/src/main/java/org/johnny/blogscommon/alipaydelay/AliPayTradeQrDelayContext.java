package org.johnny.blogscommon.alipaydelay;

import com.google.gson.FieldNamingPolicy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

/**
 * @author johnny
 * @create 2021-04-26 4:53 下午
 **/
@Service
@Slf4j
public class AliPayTradeQrDelayContext {


    public static final DelayQueue<AliPayTradeQrDelayInfo> DELAY_QUEUE = new DelayQueue<>();

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private AliPayService aliPayService;

    @PostConstruct
    public void init() {
        //启动 延迟 二维码过期 监听线程
        taskExecutor.execute(new AliPayTradeQrDelayConsumer(aliPayService));
    }

    /**
     * 构造 AliPayTradeQrDelayInfo
     */
    public static AliPayTradeQrDelayInfo buildAliPayTradeQrDelayInfo(String outTradeNo, long expireTime) {
        return new AliPayTradeQrDelayInfo(outTradeNo, expireTime);
    }
}