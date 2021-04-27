package org.johnny.blogscommon.service;

import org.johnny.blogscommon.entity.alipay.PayOrderInfo;
import org.johnny.blogscommon.form.AlipayNotifyParam;

/**
 * @author johnny
 * @create 2021-04-26 12:57 上午
 **/
public interface PayOrderService {

    PayOrderInfo buildPayOrderInfo(String subject, String outTradeNo, String money,long expireTime);

    void processSuccess(PayOrderInfo param);

    PayOrderInfo findByOutTradeNoAndAppId(String outTradeNo,String appId);

    void savePayOrderInfo(PayOrderInfo payOrderInfo);

    void deletePayOrderByOutTradeNo(String outTradeNo);
}