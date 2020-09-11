package org.johnny.blogscommon.entity.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogLeaveWord is a Querydsl query type for BlogLeaveWord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlogLeaveWord extends EntityPathBase<BlogLeaveWord> {

    private static final long serialVersionUID = 672862494L;

    public static final QBlogLeaveWord blogLeaveWord = new QBlogLeaveWord("blogLeaveWord");

    public final NumberPath<Long> blogInfoId = createNumber("blogInfoId", Long.class);

    public final NumberPath<Long> blogUserId = createNumber("blogUserId", Long.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath leaveWordHtmlContent = createString("leaveWordHtmlContent");

    public final StringPath leaveWordMdContent = createString("leaveWordMdContent");

    public final NumberPath<Long> parentLeaveWordId = createNumber("parentLeaveWordId", Long.class);

    public QBlogLeaveWord(String variable) {
        super(BlogLeaveWord.class, forVariable(variable));
    }

    public QBlogLeaveWord(Path<? extends BlogLeaveWord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogLeaveWord(PathMetadata metadata) {
        super(BlogLeaveWord.class, metadata);
    }

}

