package org.johnny.blogscommon.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuEntity is a Querydsl query type for MenuEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuEntity extends EntityPathBase<MenuEntity> {

    private static final long serialVersionUID = 1494100054L;

    public static final QMenuEntity menuEntity = new QMenuEntity("menuEntity");

    public final org.johnny.blogscommon.entity.QBaseEntity _super = new org.johnny.blogscommon.entity.QBaseEntity(this);

    public final StringPath buttonRole = createString("buttonRole");

    public final StringPath buttonRolePrefix = createString("buttonRolePrefix");

    public final StringPath component = createString("component");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    public final StringPath description = createString("description");

    public final BooleanPath enable = createBoolean("enable");

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath label = createString("label");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath path = createString("path");

    public final NumberPath<java.math.BigDecimal> sortOrder = createNumber("sortOrder", java.math.BigDecimal.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public QMenuEntity(String variable) {
        super(MenuEntity.class, forVariable(variable));
    }

    public QMenuEntity(Path<? extends MenuEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuEntity(PathMetadata metadata) {
        super(MenuEntity.class, metadata);
    }

}

