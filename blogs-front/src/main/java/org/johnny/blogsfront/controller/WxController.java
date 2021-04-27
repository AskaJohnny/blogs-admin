package org.johnny.blogsfront.controller;


import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.service.EmailService;
import org.johnny.blogscommon.service.wx.WxService;
import org.johnny.blogscommon.service.wx.WxXmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author johnny
 * @create 2019-12-15 下午12:00
 **/
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {


    @Autowired
    private WxService wxService;


    @Autowired
    private EmailService emailService;



    /**
     * 微信成为开发者 接口
     * @param signature : 签名
     * @param timestamp : 时间戳
     * @param nonce     : 随机数
     * @param echostr   : 随机字符串
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("signature") String signature,
                            @RequestParam("timestamp") Long timestamp,
                            @RequestParam("nonce") String nonce,
                            @RequestParam("echostr") String echostr) {

        log.info("【signature：{}】", signature);
        log.info("【timestamp：{}】", timestamp);
        log.info("【nonce：{}】", nonce);
        log.info("【echostr：{}】", echostr);

        return echostr;
    }

    @PostMapping("/authorize")
    public String autoResponse(HttpServletRequest request) {
        WxXmlData wxData;
        String resonseXml = "success";
        try {
            ServletInputStream in = request.getInputStream();
            int size = request.getContentLength();
            if (size < 0) {
                return null;
            }
            //解析inputstream中的数据到 WxXmlData
            wxData = wxService.resolveXmlData(in);
            if (wxData != null) {
                resonseXml = wxService.autoResponse(wxData);
                //发送email  新用户关注 通知 或者取消关注
                emailService.sendEmail(wxData);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("【Response xmlData : {}】", resonseXml);

        return resonseXml;
    }

    @PostMapping("/authorize2")
    public String autoResponse2(HttpServletRequest request) {
        WxXmlData wxData;
        String resonseXml = "success";
        try {
            ServletInputStream in = request.getInputStream();
            int size = request.getContentLength();
            if (size < 0) {
                return null;
            }
            //解析inputstream中的数据到 WxXmlData
            wxData = wxService.resolveXmlData(in);
            System.out.println(wxData);
//            if (wxData != null) {
//                resonseXml = wxService.autoResponse(wxData);
//                //发送email  新用户关注 通知 或者取消关注
//                emailService.sendEmail(wxData);
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("【Response xmlData : {}】", resonseXml);

        return resonseXml;
    }

    @GetMapping("/testxml")
    public void testXml() {


        String xml = "<xml>\n" +
                "      <ToUserName><![CDATA[gh_d0cac74031b6]]></ToUserName>\n" +
                "       <FromUserName><![CDATA[oPC_Sw17XDtjd34uaEi12hGXSmT4]]></FromUserName>\n" +
                "         <CreateTime>1576386571</CreateTime>\n" +
                "          <MsgType><![CDATA[text]]></MsgType>\n" +
                "          <Content><![CDATA[哈哈]]></Content>\n" +
                "         <MsgId>22568427643402241</MsgId>\n" +
                "         </xml>";

        XStream xStream = new XStream();

        xStream.processAnnotations(WxXmlData.class);
        xStream.alias("xml", WxXmlData.class);

        WxXmlData wxXmlData = new WxXmlData();
        wxXmlData.setContent("<![CDATA[哈哈]]>");
        wxXmlData.setCreateTime(1576386571L);
        wxXmlData.setFromUserName("<![CDATA[oPC_Sw17XDtjd34uaEi12hGXSmT4]]>");
        wxXmlData.setToUserName("<![CDATA[gh_d0cac74031b6]]>");
        wxXmlData.setMsgId("22568427643402241");
        wxXmlData.setMsgType("<![CDATA[text]]>");

        String wxStr = xStream.toXML(wxXmlData);
        System.out.println(wxStr);


        WxXmlData s = (WxXmlData) xStream.fromXML(xml);
        System.out.println(s);
    }


}