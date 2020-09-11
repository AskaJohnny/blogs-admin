package org.johnny.blogscommon.utils;

import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.vo.common.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

public class PageUtil {
    public static Pageable initPage(PageVo page) {
        Pageable pageable = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (StringUtils.isNotEmpty(sort)) {
            Sort.Direction d;
            if (StringUtils.isEmpty(order)) {
                d = Sort.Direction.DESC;
            } else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = Sort.by(d, sort);
            pageable = PageRequest.of(pageNumber, pageSize, s);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return pageable;
    }
}

