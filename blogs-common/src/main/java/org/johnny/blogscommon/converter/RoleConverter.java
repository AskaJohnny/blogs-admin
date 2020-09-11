package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author johnny
 * @create 2020-07-13 下午3:39
 **/
@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    RoleResultVo domain2vo(RoleEntity roleEntity);
}

