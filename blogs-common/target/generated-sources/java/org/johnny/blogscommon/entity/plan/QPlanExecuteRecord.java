package org.johnny.blogscommon.entity.plan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlanExecuteRecord is a Querydsl query type for PlanExecuteRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlanExecuteRecord extends EntityPathBase<PlanExecuteRecord> {

    private static final long serialVersionUID = -1871628285L;

    public static final QPlanExecuteRecord planExecuteRecord = new QPlanExecuteRecord("planExecuteRecord");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath journalContent = createString("journalContent");

    public final StringPath journalMdContent = createString("journalMdContent");

    public final StringPath journalShortContent = createString("journalShortContent");

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final StringPath progress = createString("progress");

    public final StringPath status = createString("status");

    public final NumberPath<Integer> takeTime = createNumber("takeTime", Integer.class);

    public final StringPath title = createString("title");

    public QPlanExecuteRecord(String variable) {
        super(PlanExecuteRecord.class, forVariable(variable));
    }

    public QPlanExecuteRecord(Path<? extends PlanExecuteRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanExecuteRecord(PathMetadata metadata) {
        super(PlanExecuteRecord.class, metadata);
    }

}

