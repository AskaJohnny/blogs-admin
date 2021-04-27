package org.johnny.blogscommon.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayItemOperationContext;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeCancelResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.alipaydelay.AliPayTradeQrDelayConsumer;
import org.johnny.blogscommon.alipaydelay.AliPayTradeQrDelayContext;
import org.johnny.blogscommon.config.AliPayTradeQrConfig;
import org.johnny.blogscommon.converter.PayOrderInfoConverter;
import org.johnny.blogscommon.entity.alipay.PayOrderInfo;
import org.johnny.blogscommon.enums.AliPayTradeStatusEnum;
import org.johnny.blogscommon.form.AlipayNotifyParam;
import org.johnny.blogscommon.service.AliPayService;
import org.johnny.blogscommon.service.PayOrderService;
import org.johnny.blogscommon.utils.alipay.AliPayConfigUtils;
import org.johnny.blogscommon.utils.alipay.ZxingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 支付宝 支付
 *
 * @author johnny
 * @create 2021-04-25 5:39 下午
 **/
@Service
@Slf4j
public class AliPayServiceImpl implements AliPayService {


    private String subject = "打赏 Coffee";

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private AliPayTradeQrConfig aliPayTradeQrConfig;

    private static Map<String, Object> paramMap = new HashMap<>();

    @PostConstruct
    public void init() {
        //二维码10分钟后 失效
        paramMap.put("timeout_express", aliPayTradeQrConfig.getQrExpireMinute() + "m");
    }


    static {
        AliPayConfigUtils.initConfig();
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(AliPayConfigUtils.getOptions());
    }

    @Override
    public void getPayQr(String money, ServletWebRequest request) {
        log.info("【----------------------start getPayQr--------------------------】");
        StopWatch stopWatchAll = new StopWatch();
        stopWatchAll.start();
        long currentTimes = System.currentTimeMillis();
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            String outTradeNo = "" + currentTimes
                    + (long) (Math.random() * 10000000L);
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .batchOptional(paramMap)
                    .preCreate(subject, outTradeNo, money);
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                log.info("预付款 preCreate 调用成功 业务订单号outTradeNo : {}", outTradeNo);
                // 需要修改为运行机器上的路径
                BufferedImage qrBufferedImage = ZxingUtils.getQRBufferedImage(response.getQrCode(), aliPayTradeQrConfig.getQrWidth(), aliPayTradeQrConfig.getQrHeight());
                //打印图片 将文件流放入response中
                if (qrBufferedImage != null && request.getResponse() != null) {
                    ImageIO.write(qrBufferedImage, aliPayTradeQrConfig.getQrSuffix(), request.getResponse().getOutputStream());
                }
                //保存业务订单信息
                taskExecutor.execute(() -> {
                    long expireTime = currentTimes + (aliPayTradeQrConfig.getQrExpireMinute() * 60 * 1000);
                    AliPayTradeQrDelayContext.DELAY_QUEUE.put(AliPayTradeQrDelayContext.buildAliPayTradeQrDelayInfo(outTradeNo,
                            expireTime));
                    payOrderService.buildPayOrderInfo(subject, outTradeNo, money, expireTime);
                });
            } else {
                log.error("调用失败，原因：{}", response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            log.error("调用遭遇异常，原因：{}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        stopWatchAll.stop();
        log.info("【----------------------end getPayQr 耗时: " + stopWatchAll.getTotalTimeSeconds() + "---------------------】");

    }


    /**
     * 如果是异步的回调，则安排定时任务去 执行 过期的支付订单的  撤销交易
     * 如果是主动轮训， 则轮训结束后 还没成功，则直接调用该方法  撤销交易
     *
     * @param outTradeNo
     */
    @Override
    public void judgeCancel(String outTradeNo) {
        log.info("【----------------------start judgeCancel--------------------------】");
        PayOrderInfo payOrderInfo = payOrderService.findByOutTradeNoAndAppId(outTradeNo, AliPayConfigUtils.getOptions().appId);
        if (payOrderInfo != null) {
            if (!Arrays.asList(AliPayTradeStatusEnum.TRADE_SUCCESS.name(), AliPayTradeStatusEnum.TRADE_FINISHED.name())
                    .contains(payOrderInfo.getTradeStatus())) {
                //未完成的 表示已经 过期了
                try {
                    log.info("call cancel request outTradeNo ： {}", outTradeNo);
                    AlipayTradeCancelResponse cancelResponse = Factory.Payment.Common().cancel(outTradeNo);
                    if (ResponseChecker.success(cancelResponse)) {
                        //业务订单 状态给修改一下 撤销成功 或者 删除等操作
                        log.info("call cancel request success ： {}", outTradeNo);
                        payOrderService.deletePayOrderByOutTradeNo(payOrderInfo.getOutTradeNo());
                        // payOrderInfo.setTradeStatus(AliPayTradeStatusEnum.TRADE_CLOSED.name());
                        //payOrderService.savePayOrderInfo(payOrderInfo);
                    } else {
                        log.error("【撤销交易 失败: msg: {} ,  subMsg {}】", cancelResponse.msg, cancelResponse.subMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                log.info("【outTradeNo ready success finish 】");
            }
        }
        log.info("【----------------------end  judgeCancel--------------------------】");
    }

    /**
     * 支付 成功 处理业务逻辑
     *
     * @param param
     */
    @Override
    public void processSuccess(AlipayNotifyParam param) {
        PayOrderInfo newPayOrderInfo = PayOrderInfoConverter.INSTANCE.notifyParamToDomain(param);
        payOrderService.processSuccess(newPayOrderInfo);
    }

    /**
     * 业务逻辑 校验AlipayNotifyParam
     *
     * @param alipayNotifyParam
     */
    @Override
    public void checkNotifyParam(AlipayNotifyParam alipayNotifyParam) throws AlipayApiException {
        String outTradeNo = alipayNotifyParam.getOutTradeNo();
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        log.info("【receive outTradeNo ： {}】", outTradeNo);
        PayOrderInfo payOrderInfo = payOrderService.findByOutTradeNoAndAppId(outTradeNo, AliPayConfigUtils.getOptions().appId);
        if (payOrderInfo == null) {
            throw new AlipayApiException("out_trade_no错误");
        }
//
        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        long total_amount = alipayNotifyParam.getTotalAmount().multiply(new BigDecimal(100)).longValue();
        if (total_amount != payOrderInfo.getQrCodeAmount().multiply(new BigDecimal(100)).longValue()) {
            throw new AlipayApiException("error total_amount");
        }
//
//        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
//        // 第三步可根据实际情况省略
//
        // 4、验证app_id是否为该商户本身。
        if (!alipayNotifyParam.getAppId().equals(AliPayConfigUtils.getOptions().appId)) {
            throw new AlipayApiException("app_id不一致");
        }

    }

}