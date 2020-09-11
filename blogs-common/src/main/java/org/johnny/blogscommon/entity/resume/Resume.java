package org.johnny.blogscommon.entity.resume;

import lombok.Data;

import javax.persistence.*;

/**
 * @author johnny
 * @create 2019-12-07 下午8:41
 **/
@Data
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 简历内容 markdown
     */
    @Column(columnDefinition = "text")
    private String resumeContent;

}