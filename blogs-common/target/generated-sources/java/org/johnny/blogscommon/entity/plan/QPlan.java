package org.johnny.blogscommon.entity.plan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlan is a Querydsl query type for Plan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlan extends EntityPathBase<Plan> {

    private static final long serialVersionUID = 1984062691L;

    public static final QPlan plan = new QPlan("plan");

    public final org.johnny.blogscommon.entity.QBaseEntity _super = new org.johnny.blogscommon.entity.QBaseEntity(this);

    public final StringPath award = createString("award");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.util.Date> createTime = _super.createTime;

    //inherited
    public final NumberPath<Integer> delFlag = _super.delFlag;

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath planDesc = createString("planDesc");

    public final StringPath planImage = createString("planImage");

    public final StringPath planName = createString("planName");

    public final StringPath planType = createString("planType");

    public final StringPath progress = createString("progress");

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final StringPath status = createString("status");

    public final StringPath target = createString("target");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.util.Date> updateTime = _super.updateTime;

    public QPlan(String variable) {
        super(Plan.class, forVariable(variable));
    }

    public QPlan(Path<? extends Plan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlan(PathMetadata metadata) {
        super(Plan.class, metadata);
    }

}

