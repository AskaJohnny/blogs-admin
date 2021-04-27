package org.johnny.blogscommon.alipaydelay;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.DelayQueue;

/**
 * @author johnny
 * @create 2021-04-26 4:56 下午
 **/
@Slf4j
public class AliPayTradeQrDelayConsumer implements Runnable {

    private AliPayService aliPayService;

    public AliPayTradeQrDelayConsumer(AliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }

    @Override
    public void run() {
        log.info("【监控过期二维码线程启动】");
        while (true) {
            try {
                //阻塞 获取 DELAY_QUEUE 队列中的数据 判断是否订单完成 未完成则需要去 调用alipay.trade.canal 撤销交易
                AliPayTradeQrDelayInfo aliPayTradeQrDelayInfo = AliPayTradeQrDelayContext.DELAY_QUEUE.take();
                if (StringUtils.isNotEmpty(aliPayTradeQrDelayInfo.getOutTradeNo())) {
                    //调用 撤销订单操作
                    aliPayService.judgeCancel(aliPayTradeQrDelayInfo.getOutTradeNo());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}