package org.johnny.blogsfront.controller;

/**
 * @author johnny
 * @create 2019-12-07 下午8:38
 **/


import org.johnny.blogscommon.entity.resume.Resume;
import org.johnny.blogscommon.service.ResumeService;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.resume.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {



    @Autowired
    private ResumeService resumeService;
    /**
     * 查询简历信息
     *
     * @return : ResultVo
     */
    @GetMapping()
    @CrossOrigin
    public ResultVo<Resume> queryById() {
        ResumeVo resumeVo = resumeService.queryResume();
        return ResultVoUtil.success(resumeVo);

    }

}