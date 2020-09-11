package org.johnny.blogscommon.entity.ipaccess;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * IpAccessInfo
 *
 * @author johnny
 * @create 2020-08-14 下午2:20
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class IpAccessInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String city;
    /**
     * 运营商
     */
    private String operators;

}