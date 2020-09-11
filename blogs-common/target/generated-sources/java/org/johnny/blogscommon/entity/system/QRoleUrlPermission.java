package org.johnny.blogscommon.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleUrlPermission is a Querydsl query type for RoleUrlPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleUrlPermission extends EntityPathBase<RoleUrlPermission> {

    private static final long serialVersionUID = -561799404L;

    public static final QRoleUrlPermission roleUrlPermission = new QRoleUrlPermission("roleUrlPermission");

    public final org.johnny.blogscommon.entity.QBaseEntity _super = new org.johnny.blogscommon.entity.QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public final NumberPath<Long> urlId = createNumber("urlId", Long.class);

    public QRoleUrlPermission(String variable) {
        super(RoleUrlPermission.class, forVariable(variable));
    }

    public QRoleUrlPermission(Path<? extends RoleUrlPermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleUrlPermission(PathMetadata metadata) {
        super(RoleUrlPermission.class, metadata);
    }

}

