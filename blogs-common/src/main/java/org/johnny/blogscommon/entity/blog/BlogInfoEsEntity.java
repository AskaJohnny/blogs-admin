package org.johnny.blogscommon.entity.blog;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

/**
 * @author johnny
 * @create 2019-12-01 上午11:51
 **/

@Data
@Document(indexName = "blog", type = "blogInfo")
public class BlogInfoEsEntity {

    @Id
    private String id;

    /**
     * 博客标题
     */
    @Field(analyzer = "ik_max_word", type = FieldType.Text, searchAnalyzer = "ik_max_word")
    private String blogTitle;


    private Long blogInfoId;

}