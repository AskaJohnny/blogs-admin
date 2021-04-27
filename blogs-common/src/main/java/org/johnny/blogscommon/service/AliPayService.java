package org.johnny.blogscommon.service;

import com.alipay.api.AlipayApiException;
import org.johnny.blogscommon.form.AlipayNotifyParam;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author johnny
 * @create 2021-04-25 5:39 下午
 **/
public interface AliPayService {


    void getPayQr(String money, ServletWebRequest servletWebRequest);

    void judgeCancel(String outTradeNo);

    void processSuccess(AlipayNotifyParam param);

    void checkNotifyParam(AlipayNotifyParam param) throws AlipayApiException;


}