package org.johnny.blogscommon.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleMenuEntity is a Querydsl query type for RoleMenuEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleMenuEntity extends EntityPathBase<RoleMenuEntity> {

    private static final long serialVersionUID = -569790612L;

    public static final QRoleMenuEntity roleMenuEntity = new QRoleMenuEntity("roleMenuEntity");

    public final org.johnny.blogscommon.entity.QBaseEntity _super = new org.johnny.blogscommon.entity.QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public QRoleMenuEntity(String variable) {
        super(RoleMenuEntity.class, forVariable(variable));
    }

    public QRoleMenuEntity(Path<? extends RoleMenuEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleMenuEntity(PathMetadata metadata) {
        super(RoleMenuEntity.class, metadata);
    }

}

