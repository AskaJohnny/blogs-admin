package org.johnny.blogscommon.service.impl;


import org.johnny.blogscommon.entity.resume.Resume;
import org.johnny.blogscommon.repository.resume.ResumeRepository;
import org.johnny.blogscommon.service.ResumeService;
import org.johnny.blogscommon.vo.resume.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author johnny
 * @create 2019-12-07 下午8:43
 **/
@Service
public class ResumeServiceImpl implements ResumeService {


    @Autowired
    private ResumeRepository resumeRepository;


    @Override
    public ResumeVo queryResume() {
        List<Resume> resumes = resumeRepository.findAll();
        Resume resume = resumes.get(0);
        ResumeVo resumeVo = new ResumeVo();
        resumeVo.setId(resume.getId());
        resumeVo.setResumeContent(resume.getResumeContent());
        return resumeVo;
    }
}