package org.johnny.blogscommon.repository.alipay;

import org.johnny.blogscommon.entity.alipay.PayOrderInfo;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blogInfo repository
 *
 * @author johnny
 * @create 2019-11-23 下午7:07
 **/
@Repository
public interface PayOrderInfoRepository extends JpaRepository<PayOrderInfo, String> {


    PayOrderInfo findByOutTradeNoAndAppId(String outTradeNo , String appId);

}