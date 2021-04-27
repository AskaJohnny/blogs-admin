package org.johnny.blogscommon.entity.thumb;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 用户 点赞 关系
 *
 * @author johnny
 * @create 2020-12-21 11:06 上午
 **/
@Data
@Entity
public class UserThumbRelation extends BaseEntity{

    private Long userId;

    private Long blogInfoId;

}