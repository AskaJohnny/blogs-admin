package org.johnny.blogscommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author johnny
 * @create 2020-08-18 下午2:47
 **/
@AllArgsConstructor
@Getter
public enum PlanTypeEnum {


    /**
     * 学习类型 状态
     */
    STUDY("学习", "study"),
    /**
     * 健康类型
     */
    HEALTH("健康", "health"),

    /**
     * 素质类型
     */

    QUALITY("素质", "quality");

    private String msg;

    private String value;

}