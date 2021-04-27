package org.johnny.blogscommon.service.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.entity.alipay.PayOrderInfo;
import org.johnny.blogscommon.enums.AliPayTradeStatusEnum;
import org.johnny.blogscommon.repository.alipay.PayOrderInfoRepository;
import org.johnny.blogscommon.service.PayOrderService;
import org.johnny.blogscommon.utils.alipay.AliPayConfigUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author johnny
 * @create 2021-04-26 12:58 上午
 **/
@Service
@Slf4j
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    private PayOrderInfoRepository payOrderInfoRepository;


    @Override
    public PayOrderInfo buildPayOrderInfo(String subject, String outTradeNo, String money, long expireTime) {
        Config options = AliPayConfigUtils.getOptions();
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setAppId(options.appId);
        payOrderInfo.setQrCodeAmount(new BigDecimal(money));
        payOrderInfo.setSubject(subject);
        payOrderInfo.setOutTradeNo(outTradeNo);
        payOrderInfo.setTradeStatus(AliPayTradeStatusEnum.TRADE_PRECREATE.name());
        payOrderInfo.setExpireTime(expireTime);
        payOrderInfoRepository.save(payOrderInfo);
        return payOrderInfo;
    }

    @Override
    public void processSuccess(PayOrderInfo param) {
        String outTradeNo = param.getOutTradeNo();
        log.info("【processSuccess outTradeNo : {}】", outTradeNo);
        payOrderInfoRepository.findById(outTradeNo).ifPresent(payOrderInfo -> {
            BigDecimal qrCodeAmount = payOrderInfo.getQrCodeAmount();
            BeanUtils.copyProperties(param, payOrderInfo);
            payOrderInfo.setQrCodeAmount(qrCodeAmount);

            payOrderInfoRepository.save(payOrderInfo);
            log.info("【processSuccess payOrderInfo : {}】", payOrderInfo);
        });

    }

    @Override
    public PayOrderInfo findByOutTradeNoAndAppId(String outTradeNo, String appId) {
        if (StringUtils.isNotEmpty(outTradeNo) && StringUtils.isNotEmpty(appId)) {
            return payOrderInfoRepository.findByOutTradeNoAndAppId(outTradeNo, appId);
        }
        return null;
    }

    @Override
    public void savePayOrderInfo(PayOrderInfo payOrderInfo) {
        payOrderInfoRepository.save(payOrderInfo);
    }

    @Override
    public void deletePayOrderByOutTradeNo(String outTradeNo) {
        if (StringUtils.isNotEmpty(outTradeNo)) {
            payOrderInfoRepository.deleteById(outTradeNo);
        }
    }


}