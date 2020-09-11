package org.johnny.blogscommon.entity.ipaccess;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author johnny
 * @create 2020-08-14 下午2:30
 **/
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class IpAccessCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;


    private Long count;

}