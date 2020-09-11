package org.johnny.blogscommon.service.wx;


import java.io.IOException;
import java.io.InputStream;

/**
 * 微信Service
 *
 * @author johnny
 * @create 2019-12-15 下午12:54
 **/

public interface WxService {
    WxXmlData resolveXmlData(InputStream in) throws IOException;

    String autoResponse(WxXmlData wxData);
}