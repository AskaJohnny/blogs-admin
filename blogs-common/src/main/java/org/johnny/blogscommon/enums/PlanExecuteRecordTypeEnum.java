package org.johnny.blogscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @create 2020-08-18 下午2:47
 **/
@AllArgsConstructor
@Getter
public enum PlanExecuteRecordTypeEnum {


    /**
     * 执行 状态
     */
    EXECUTE("执行天数", "execute"),
    /**
     * 特殊状态 特殊情况
     */
    SPECIAL("特殊天数", "special"),

    /**
     * 偷懒状态
     */
    LAZY("偷懒天数", "lazy");

    private String msg;

    private String value;

}