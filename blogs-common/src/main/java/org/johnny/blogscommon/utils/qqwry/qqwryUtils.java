package org.johnny.blogscommon.utils.qqwry;

import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;

import java.io.IOException;

/**
 * QQ 纯真Ip 解析 Utils
 *
 * @author johnny
 * @create 2020-08-13 上午8:59
 **/
public class qqwryUtils {


    public static void main(String[] args) throws IOException {

        QQWry qqWry = new QQWry();
        IPZone ipZone = qqWry.findIP("58.214.17.66");
        System.out.printf("%s, %s", ipZone.getMainInfo(), ipZone.getSubInfo());
    }

}