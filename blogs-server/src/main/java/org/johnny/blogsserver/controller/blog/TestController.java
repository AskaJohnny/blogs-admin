package org.johnny.blogsserver.controller.blog;

import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author johnny
 * @create 2020-11-11 下午5:45
 **/

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/some")
    @CrossOrigin
    public String some() {

        return "Hello";
    }


    public static void main(String[] args) {


        String playUrl = "http://ts7.sztv.com.cn/szmg/vod/2020/10/13/651d31ac2fc84008a496c67b935cd748/_h264_1200k_mp4.mp4";
        String regUrl = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        Pattern p = Pattern.compile(regUrl);
        Matcher m = p.matcher(playUrl);
        System.out.println("Hello");

        if (m.find()) {
            System.out.println("true");
        }

        System.out.println("Jack");
    }


}