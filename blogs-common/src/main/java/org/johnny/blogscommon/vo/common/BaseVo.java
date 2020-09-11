package org.johnny.blogscommon.vo.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.johnny.blogscommon.constants.NumberConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 基础Vo
 *
 * @author johnny
 * @create 2020-07-13 下午3:10
 **/
@Data
public class BaseVo  implements  QueryConditionVo{

    private Long id;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;

    private Integer delFlag = NumberConstants.NO_DELETE_STATUS;
}