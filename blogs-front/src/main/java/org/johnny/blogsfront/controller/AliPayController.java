package org.johnny.blogsfront.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.enums.AliPayTradeStatusEnum;
import org.johnny.blogscommon.form.AlipayNotifyParam;
import org.johnny.blogscommon.service.AliPayService;
import org.johnny.blogscommon.utils.alipay.AliPayConfigUtils;
import org.johnny.blogscommon.utils.alipay.ZxingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author johnny
 * @create 2021-04-25 5:18 下午
 **/
@RestController
@RequestMapping("/alipay")
@Slf4j
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private GsonBuilder gsonBuilder = new GsonBuilder();

    private Gson gson = null;

    @PostConstruct
    public void init() {
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();
        log.info("【线程池创建成功】");
        //executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    @RequestMapping("/qrCode")
    @CrossOrigin
    public void getPayQr(String money, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(money)) {
            money = "1";
        }
        aliPayService.getPayQr(money, new ServletWebRequest(request, response));
    }

    /**
     * https://opendocs.alipay.com/open/194/103296
     *
     * @param request
     * @return
     */
    @RequestMapping("/callback")
    @CrossOrigin
    public String callback(HttpServletRequest request) {
        // 将异步通知中收到的待验证所有参数都存放到map中
        Map<String, String> params = convertRequestParamsToMap(request);
        String paramsJson = gson.toJson(params);
        log.info("支付宝回调，{}", paramsJson);
        try {
            // 支付宝配置 老版本这样做 ，新版本使用  Factory.setOptions(AliPayConfigUtils.getOptions());
            //AlipayConfig alipayConfig = new AlipayConfig();
            boolean signVerified = false;
            // 调用SDK验证签名
//            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipay_public_key(),
//                    alipayConfig.getCharset(), alipayConfig.getSigntype());
            try {
                signVerified = Factory.Payment.Common().verifyNotify(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (signVerified) {
                log.info("支付宝回调签名认证成功");
                AlipayNotifyParam alipayNotifyParam = buildAlipayNotifyParam(params);
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                aliPayService.checkNotifyParam(alipayNotifyParam);
                // 另起线程处理业务
                taskExecutor.execute(() -> {
                    String tradeStatus = alipayNotifyParam.getTradeStatus();
                    // 支付成功
                    if (AliPayTradeStatusEnum.TRADE_SUCCESS.name().equals(tradeStatus)
                            || AliPayTradeStatusEnum.TRADE_FINISHED.name().equals(tradeStatus)) {
                        try {
                            // 处理支付成功逻辑
                            aliPayService.processSuccess(alipayNotifyParam);
                            log.info("【支付 回调成功： 】");
                        } catch (Exception e) {
                            log.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                        }
                    } else if (AliPayTradeStatusEnum.TRADE_CLOSED.name().equals(tradeStatus)) {
                        log.error("支付宝回调业务，支付宝交易状态：{}, params:{}", tradeStatus, paramsJson);
                        aliPayService.processSuccess(alipayNotifyParam);
                    } else {
                        log.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}", tradeStatus, paramsJson);
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return "success";
            } else {
                log.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return "failure";
        }
    }


    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = gson.toJson(params);
        return gson.fromJson(json, AlipayNotifyParam.class);
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param params
     */
    private void check(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");

        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        log.info("【receive outTradeNo ： {}】", outTradeNo);
//        Order order = getOrderByOutTradeNo(outTradeNo); // 这个方法自己实现
//        if (order == null) {
//            throw new AlipayApiException("out_trade_no错误");
//        }
//
//        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
//        if (total_amount != order.getPayPrice().longValue()) {
//            throw new AlipayApiException("error total_amount");
//        }
//
//        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
//        // 第三步可根据实际情况省略
//
//        // 4、验证app_id是否为该商户本身。
//        if (!params.get("app_id").equals(alipayConfig.getAppid())) {
//            throw new AlipayApiException("app_id不一致");
//        }
    }


    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }


    public static void main(String[] args) {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(AliPayConfigUtils.getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                // 需要修改为运行机器上的路径
                String filePath = String.format("/Users/johnny/Downloads/qr-%s.png",
                        response.getOutTradeNo());
                System.out.println("filePath:" + filePath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);


            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }


}