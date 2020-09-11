package org.johnny.blogscommon.utils;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;

import java.io.IOException;

/**
 * QQ 纯真Ip 解析 Utils
 *
 * @author johnny
 * @create 2020-08-13 上午8:59
 **/
public class QQWryUtils {

    private static QQWry qqWry;

    static {
        try {
            qqWry = new QQWry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        QQWry qqWry = new QQWry();
        IPZone ipZone = qqWry.findIP("58.214.17.66");
        System.out.printf("%s, %s", ipZone.getMainInfo(), ipZone.getSubInfo());
    }

    public static IPZone getIpZoneByIp(String ip) {
        return qqWry.findIP(ip);
    }
}