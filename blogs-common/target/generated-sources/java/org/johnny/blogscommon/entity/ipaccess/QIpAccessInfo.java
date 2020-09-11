package org.johnny.blogscommon.entity.ipaccess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIpAccessInfo is a Querydsl query type for IpAccessInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIpAccessInfo extends EntityPathBase<IpAccessInfo> {

    private static final long serialVersionUID = 377038609L;

    public static final QIpAccessInfo ipAccessInfo = new QIpAccessInfo("ipAccessInfo");

    public final StringPath city = createString("city");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath operators = createString("operators");

    public QIpAccessInfo(String variable) {
        super(IpAccessInfo.class, forVariable(variable));
    }

    public QIpAccessInfo(Path<? extends IpAccessInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIpAccessInfo(PathMetadata metadata) {
        super(IpAccessInfo.class, metadata);
    }

}

