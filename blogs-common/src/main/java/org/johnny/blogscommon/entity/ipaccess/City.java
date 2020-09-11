package org.johnny.blogscommon.entity.ipaccess;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author johnny
 * @create 2020-08-14 下午3:14
 **/
@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String longitude;

    private String latitude;



}