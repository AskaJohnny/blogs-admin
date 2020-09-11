package org.johnny.blogscommon.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @author johnny
 * @create 2020-07-18 下午6:41
 **/
public class IdUtils {


    private static final String replaceFrom = "-";
    private static final String replaceTo = "";

    private IdUtils() {

    }

    public static String simpleUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), replaceFrom, replaceTo);
    }
}