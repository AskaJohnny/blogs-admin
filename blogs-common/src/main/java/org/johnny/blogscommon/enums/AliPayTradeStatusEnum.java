package org.johnny.blogscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @create 2021-04-26 3:18 下午
 **/
@AllArgsConstructor
@Getter
public enum AliPayTradeStatusEnum {

    TRADE_PRECREATE("预交易", "TRADE_PRECREATE"),
    /**
     * 执行 状态
     */
    TRADE_SUCCESS("交易成功", "TRADE_SUCCESS"),


    TRADE_FINISHED("交易结束", "TRADE_FINISHED"),

    TRADE_CLOSED("交易撤销", "TRADE_CLOSED");

    private String msg;

    private String value;
}