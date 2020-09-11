package org.johnny.blogscommon.utils;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author johnny
 * @create 2020-07-18 下午6:44
 **/
public class GsonUtils {

    private static Gson gson = new Gson();

    public static final String toJsonStr(Object o) {
        return gson.toJson(o);
    }

    public static  final List fromJsonToList(String jsonStr){
        return gson.fromJson(jsonStr, List.class);
    }
}