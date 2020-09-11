package org.johnny.blogsfront.controller;


import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.QiniuAccessUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传Controller
 *
 * @author johnny
 * @create 2019-12-05 上午10:59
 **/
@RequestMapping("/image")
@Slf4j
@RestController
public class ImageController  implements ApplicationContextAware {

    private String activeProfile;

    @PostMapping("/upload")
    @CrossOrigin
    public ResultVo upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultVoUtil.error(500, "上传失败,请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "";
        if(activeProfile.equals("prod")){
             filePath = "/usr/local/blogs/";
        }else if(activeProfile.equals("dev")){
             filePath = "/Users/johnny/Downloads/";
        }
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("【上传文件失败 异常:{} 】 " , e.getMessage());
        }
        String destFilePath = filePath + fileName;
        log.info("【上传文件本地路径 destFilePath : {}】"  , destFilePath);
        log.info("【开始上传七牛云图片服务】");
        String qiniuUrl = QiniuAccessUtils.UploadPic(destFilePath, fileName);
        if(dest.exists()){
            dest.delete();
        }
        log.info("【七牛云图片url : {} 】"  , qiniuUrl);
        return ResultVoUtil.success(qiniuUrl);
    }

    @GetMapping("/upload2")
    public ResultVo upload() {
        String filePath = "/Users/johnny/Downloads/elasticsearch.jpeg";
        log.info("【开始上传七牛云图片服务】");
        String qiniuUrl = QiniuAccessUtils.UploadPic(filePath, "elasticsearch");
        log.info("【七牛云图片url : {} 】"  , qiniuUrl);
        return ResultVoUtil.success(qiniuUrl);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        activeProfile = applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}