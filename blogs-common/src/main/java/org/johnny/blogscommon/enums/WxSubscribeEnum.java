package org.johnny.blogscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信订阅enum
 *
 * @author johnny
 * @create 2019-12-15 下午2:45
 **/
@AllArgsConstructor
@Getter
public enum WxSubscribeEnum {

    SUBSCRIBE("订阅", "subscribe"),
    UNSUBSCRIBE("取消订阅", "unsubscribe");

    private String msg;

    private String value;


}