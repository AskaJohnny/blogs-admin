package org.johnny.blogscommon.entity.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogInfo is a Querydsl query type for BlogInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlogInfo extends EntityPathBase<BlogInfo> {

    private static final long serialVersionUID = -409458735L;

    public static final QBlogInfo blogInfo = new QBlogInfo("blogInfo");

    public final StringPath anchorJson = createString("anchorJson");

    public final StringPath blogContent = createString("blogContent");

    public final StringPath blogImageUrl = createString("blogImageUrl");

    public final StringPath blogMdContent = createString("blogMdContent");

    public final StringPath blogShortContent = createString("blogShortContent");

    public final StringPath blogTitle = createString("blogTitle");

    public final NumberPath<Integer> blogTypeId = createNumber("blogTypeId", Integer.class);

    public final NumberPath<Long> clickCount = createNumber("clickCount", Long.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath createMonth = createString("createMonth");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath createUser = createString("createUser");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> thumbCount = createNumber("thumbCount", Long.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QBlogInfo(String variable) {
        super(BlogInfo.class, forVariable(variable));
    }

    public QBlogInfo(Path<? extends BlogInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogInfo(PathMetadata metadata) {
        super(BlogInfo.class, metadata);
    }

}

