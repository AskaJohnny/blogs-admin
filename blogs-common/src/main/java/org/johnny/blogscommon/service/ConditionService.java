package org.johnny.blogscommon.service;

import com.querydsl.jpa.impl.JPAQuery;
import org.johnny.blogscommon.vo.common.BaseVo;
import org.johnny.blogscommon.vo.common.QueryConditionVo;

/**
 * 查询Service
 *
 * @author johnny
 * @create 2020-07-13 下午5:08
 **/
public interface ConditionService<T> {

    /**
     * 抽象了 查询接口
     *
     * @param jpaQuery         : querydsl 的 jpaQuery
     * @param queryConditionVo : 实现了 queryConditionVo接口的 前端传入的 查询参数
     */
    void fillCondition(JPAQuery<T> jpaQuery, QueryConditionVo queryConditionVo);
}