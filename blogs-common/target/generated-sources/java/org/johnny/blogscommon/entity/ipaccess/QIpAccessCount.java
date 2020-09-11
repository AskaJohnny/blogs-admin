package org.johnny.blogscommon.entity.ipaccess;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIpAccessCount is a Querydsl query type for IpAccessCount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIpAccessCount extends EntityPathBase<IpAccessCount> {

    private static final long serialVersionUID = -1202201844L;

    public static final QIpAccessCount ipAccessCount = new QIpAccessCount("ipAccessCount");

    public final StringPath city = createString("city");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath province = createString("province");

    public QIpAccessCount(String variable) {
        super(IpAccessCount.class, forVariable(variable));
    }

    public QIpAccessCount(Path<? extends IpAccessCount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIpAccessCount(PathMetadata metadata) {
        super(IpAccessCount.class, metadata);
    }

}

