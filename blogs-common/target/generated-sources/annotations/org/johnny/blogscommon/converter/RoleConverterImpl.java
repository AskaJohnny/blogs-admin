package org.johnny.blogscommon.converter;

import java.text.SimpleDateFormat;
import javax.annotation.Generated;
import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-09T10:52:25+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleResultVo domain2vo(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResultVo roleResultVo = new RoleResultVo();

        if ( roleEntity.getCreateTime() != null ) {
            roleResultVo.setCreateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( roleEntity.getCreateTime() ) );
        }
        if ( roleEntity.getUpdateTime() != null ) {
            roleResultVo.setUpdateTime( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( roleEntity.getUpdateTime() ) );
        }
        roleResultVo.setId( roleEntity.getId() );
        roleResultVo.setCreateBy( roleEntity.getCreateBy() );
        roleResultVo.setUpdateBy( roleEntity.getUpdateBy() );
        roleResultVo.setDelFlag( roleEntity.getDelFlag() );
        roleResultVo.setRoleName( roleEntity.getRoleName() );
        roleResultVo.setRoleDesc( roleEntity.getRoleDesc() );

        return roleResultVo;
    }
}
