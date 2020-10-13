package org.johnny.blogscommon.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Generated;
import org.johnny.blogscommon.entity.user.UserEntity;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:16:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserResultVo domain2vo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResultVo userResultVo = new UserResultVo();

        if ( userEntity.getCreateTime() != null ) {
            userResultVo.setCreateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( userEntity.getCreateTime() ) );
        }
        if ( userEntity.getUpdateTime() != null ) {
            userResultVo.setUpdateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( userEntity.getUpdateTime() ) );
        }
        userResultVo.setId( userEntity.getId() );
        userResultVo.setCreateBy( userEntity.getCreateBy() );
        userResultVo.setUpdateBy( userEntity.getUpdateBy() );
        userResultVo.setDelFlag( userEntity.getDelFlag() );
        userResultVo.setUsername( userEntity.getUsername() );
        userResultVo.setPassword( userEntity.getPassword() );
        userResultVo.setGender( userEntity.getGender() );
        userResultVo.setPhone( userEntity.getPhone() );
        userResultVo.setEmail( userEntity.getEmail() );
        userResultVo.setHeadImage( userEntity.getHeadImage() );
        userResultVo.setEnable( userEntity.getEnable() );

        return userResultVo;
    }

    @Override
    public UserEntity vo2domain(UserReqVo userReqVo) {
        if ( userReqVo == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        try {
            if ( userReqVo.getCreateTime() != null ) {
                userEntity.setCreateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( userReqVo.getCreateTime() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( userReqVo.getUpdateTime() != null ) {
                userEntity.setUpdateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( userReqVo.getUpdateTime() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        userEntity.setId( userReqVo.getId() );
        userEntity.setCreateBy( userReqVo.getCreateBy() );
        userEntity.setUpdateBy( userReqVo.getUpdateBy() );
        userEntity.setDelFlag( userReqVo.getDelFlag() );
        userEntity.setUsername( userReqVo.getUsername() );
        userEntity.setPassword( userReqVo.getPassword() );
        userEntity.setGender( userReqVo.getGender() );
        userEntity.setPhone( userReqVo.getPhone() );
        userEntity.setEmail( userReqVo.getEmail() );
        userEntity.setHeadImage( userReqVo.getHeadImage() );
        userEntity.setEnable( userReqVo.getEnable() );

        return userEntity;
    }
}
