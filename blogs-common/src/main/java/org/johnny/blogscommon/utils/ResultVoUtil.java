package org.johnny.blogscommon.utils;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;
import org.johnny.blogscommon.vo.common.ResultVo;


@Data
public class ResultVoUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setData(object);
        resultVO.setCode(200);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo expire(){
        ResultVo expireResultVo = new ResultVo();
        expireResultVo.setCode(50008);
        expireResultVo.setMsg("success");
        return expireResultVo;
    }
    public static ResultVo loginFail(){
        ResultVo expireResultVo = new ResultVo();
        expireResultVo.setCode(40000);
        expireResultVo.setMsg("账号或密码错误!");
        return expireResultVo;
    }

    public static ResultVo accessReject(){
        ResultVo expireResultVo = new ResultVo();
        expireResultVo.setCode(50403);
        expireResultVo.setMsg("无权访问资源!");
        return expireResultVo;
    }


    public static ResultVo logOut(){
        ResultVo logOutResultVo = new ResultVo();
        logOutResultVo.setCode(50012);
        logOutResultVo.setMsg("success");
        return logOutResultVo;
    }


    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
