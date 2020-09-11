package org.johnny.blogscommon.vo.common;

import lombok.Data;

import java.io.Serializable;


@Data
public class ResultVo<T> implements Serializable {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    /**
     * success
     *
     * @param o
     * @return
     */
    public static ResultVo success(Object o) {
        ResultVo resultVO = instance();
        resultVO.setCode(200);
        resultVO.setMsg(ResultEnum.SUCCESS.name());
        resultVO.setData(o);
        return resultVO;
    }

    /**
     * error
     *
     * @param o
     * @return
     */
    public static ResultVo error(Object o) {
        ResultVo resultVO = instance();
        resultVO.setCode(500);
        resultVO.setMsg(ResultEnum.ERROR.name());
        resultVO.setData(o);
        return resultVO;
    }

    private static ResultVo instance() {
        return new ResultVo();
    }

    public enum ResultEnum {
        /**
         * 成功
         */
        SUCCESS,
        /**
         * 失败
         */
        ERROR;
    }
}
