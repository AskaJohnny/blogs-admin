package org.johnny.blogscommon.service.impl.wx;

import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.enums.WxSubscribeEnum;
import org.johnny.blogscommon.service.wx.WxService;
import org.johnny.blogscommon.service.wx.WxXmlData;
import org.johnny.blogscommon.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 微信Service impl
 *
 * @author johnny
 * @create 2019-12-15 下午12:55
 **/
@Service
@Slf4j
public class WxServiceImpl implements WxService {


    /**
     * <xml>
     * <ToUserName><![CDATA[gh_d0cac74031b6]]></ToUserName>
     * <FromUserName><![CDATA[oPC_Sw17XDtjd34uaEi12hGXSmT4]]></FromUserName>
     * <CreateTime>1576386571</CreateTime>
     * <MsgType><![CDATA[text]]></MsgType>
     * <Content><![CDATA[哈哈]]></Content>
     * <MsgId>22568427643402241</MsgId>
     * </xml>
     *
     * @param in
     * @return
     * @throws IOException
     */
    @Override
    public WxXmlData resolveXmlData(InputStream in) throws IOException {
        String xmlData = FileUtils.getInputToString(in);
        log.info("【receive  xmlData str : {}】", xmlData);
        WxXmlData wxXmlData = null;
        try {
            XStream xstream = new XStream();
            //这个必须要加 不然无法转换成WxXmlData对象
            xstream.setClassLoader(WxXmlData.class.getClassLoader());
            xstream.processAnnotations(WxXmlData.class);
            xstream.alias("xml", WxXmlData.class);
            wxXmlData = (WxXmlData) xstream.fromXML(xmlData);
            log.info("【wxXmlData: {}】 ", wxXmlData);
        } catch (Exception e) {
            log.error("【error】{}", e.getMessage());
        }
        return wxXmlData;
    }


    @Override
    public String autoResponse(WxXmlData wxData) {

        WxXmlData resultXmlData = new WxXmlData();
        resultXmlData.setToUserName(wxData.getFromUserName());
        resultXmlData.setFromUserName(wxData.getToUserName());
        if (!StringUtils.isEmpty(wxData.getEvent())) {
            if (WxSubscribeEnum.SUBSCRIBE.getValue().equals(wxData.getEvent())) {
                resultXmlData.setMsgType("text");
                resultXmlData.setCreateTime(System.currentTimeMillis());
                resultXmlData.setContent("欢迎来到Johnny屋,本公众号会定期更新技术干货,愿与 读者共同成长。\n\n" +
                        "-<a href=\"https://www.askajohnny.com\">我的博客 https://www.askajohnny.com (建议PC端打开,移动端适配正在紧张开发中)</a>");
            }
        } else if (wxData.getContent().equals("杜康宇")) {
            resultXmlData.setMsgType("text");
            resultXmlData.setCreateTime(System.currentTimeMillis());
            resultXmlData.setContent("阿强爱你哦 嘻嘻。");
        } else if(wxData.getContent().equalsIgnoreCase("vip")){
            resultXmlData.setMsgType("text");
            resultXmlData.setCreateTime(System.currentTimeMillis());
            resultXmlData.setContent("<a href=\"https://my.openwrite.cn/code/generate?blogId=18931-1576559666626-322\">点击该链接，获取博客解锁验证码</a>");
        } else {
            resultXmlData.setMsgType("text");
            resultXmlData.setCreateTime(System.currentTimeMillis());
            resultXmlData.setContent("公众号正在开发中。后期请多多关注！");
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(WxXmlData.class);
        xstream.setClassLoader(WxXmlData.class.getClassLoader());
        return xstream.toXML(resultXmlData);

//        return "<xml>\n" +
//                "  <ToUserName><![CDATA[oPC_Sw17XDtjd34uaEi12hGXSmT4]]></ToUserName>\n" +
//                "  <FromUserName><![CDATA[gh_d0cac74031b6]]></FromUserName>\n" +
//                "  <CreateTime>1576390360200</CreateTime>\n" +
//                "  <MsgType><![CDATA[news]]></MsgType>\n" +
//                "  <ArticleCount>1</ArticleCount>\n" +
//                "  <Articles>\n" +
//                "    <item>\n" +
//                "      <Title><![CDATA[Johnny屋]]></Title>\n" +
//                "      <Description><![CDATA[欢迎来到Johnny屋]]></Description>\n" +
//                "      <PicUrl><![CDATA[http://q1xacbncg.bkt.clouddn.com/restful.jpeg]]></PicUrl>\n" +
//                "      <Url><![CDATA[https://www.askajohnny.com]]></Url>\n" +
//                "    </item>\n" +
//                "  </Articles>\n" +
//                "</xml>";
    }
}