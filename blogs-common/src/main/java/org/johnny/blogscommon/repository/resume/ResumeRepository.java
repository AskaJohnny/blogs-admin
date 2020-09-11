package org.johnny.blogscommon.repository.resume;

import org.johnny.blogscommon.entity.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author johnny
 * @create 2019-11-29 上午9:09
 **/
public interface ResumeRepository extends JpaRepository<Resume, Long> {


}