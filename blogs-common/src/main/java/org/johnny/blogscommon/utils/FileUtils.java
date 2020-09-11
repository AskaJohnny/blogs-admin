package org.johnny.blogscommon.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * File 工具类
 * @author johnny
 * @create 2019-12-15 下午12:52
 **/

public class FileUtils {

    /**
     *
     * @param in : InputStream
     * @return  ：String
     * @throws IOException
     */
    public static  String getInputToString(InputStream in) throws IOException {

        StringBuffer sb = new StringBuffer();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, len, "UTF-8"));
        }
        return sb.toString();
    }

}