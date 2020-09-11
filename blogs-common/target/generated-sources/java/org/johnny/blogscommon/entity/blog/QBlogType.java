package org.johnny.blogscommon.entity.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogType is a Querydsl query type for BlogType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlogType extends EntityPathBase<BlogType> {

    private static final long serialVersionUID = -409120163L;

    public static final QBlogType blogType = new QBlogType("blogType");

    public final StringPath blogTypeAnchor = createString("blogTypeAnchor");

    public final StringPath createTime = createString("createTime");

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath typeDesc = createString("typeDesc");

    public final StringPath typeName = createString("typeName");

    public QBlogType(String variable) {
        super(BlogType.class, forVariable(variable));
    }

    public QBlogType(Path<? extends BlogType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogType(PathMetadata metadata) {
        super(BlogType.class, metadata);
    }

}

