package org.johnny.blogscommon.vo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNumber;

    private int pageSize;

    private String sort;

    private String order;
}
