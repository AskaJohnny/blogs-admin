package org.johnny.blogscommon.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUrlPermission is a Querydsl query type for UrlPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUrlPermission extends EntityPathBase<UrlPermission> {

    private static final long serialVersionUID = -1946778134L;

    public static final QUrlPermission urlPermission = new QUrlPermission("urlPermission");

    public final org.johnny.blogscommon.entity.QBaseEntity _super = new org.johnny.blogscommon.entity.QBaseEntity(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath shortDesc = createString("shortDesc");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public final StringPath urlRequest = createString("urlRequest");

    public QUrlPermission(String variable) {
        super(UrlPermission.class, forVariable(variable));
    }

    public QUrlPermission(Path<? extends UrlPermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUrlPermission(PathMetadata metadata) {
        super(UrlPermission.class, metadata);
    }

}

