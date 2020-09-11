package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.entity.user.UserEntity;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author johnny
 * @create 2020-07-13 下午5:04
 **/
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserResultVo domain2vo(UserEntity userEntity);

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserEntity vo2domain(UserReqVo userReqVo);
}