package org.johnny.blogscommon.entity.system;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2020-07-10 下午7:31
 **/
@Data
public class Menu {

    private Long id;
    private String path;

    private String name;

    private Map<String,Object> meta;

    private String component;

    private List<Menu> children;
}