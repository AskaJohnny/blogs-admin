package org.johnny.blogscommon.entity.blog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlogUserInfo is a Querydsl query type for BlogUserInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlogUserInfo extends EntityPathBase<BlogUserInfo> {

    private static final long serialVersionUID = 1918066876L;

    public static final QBlogUserInfo blogUserInfo = new QBlogUserInfo("blogUserInfo");

    public final StringPath city = createString("city");

    public final StringPath gender = createString("gender");

    public final StringPath headImage = createString("headImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath passWord = createString("passWord");

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath providerId = createString("providerId");

    public final StringPath province = createString("province");

    public final StringPath role = createString("role");

    public final StringPath userName = createString("userName");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QBlogUserInfo(String variable) {
        super(BlogUserInfo.class, forVariable(variable));
    }

    public QBlogUserInfo(Path<? extends BlogUserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogUserInfo(PathMetadata metadata) {
        super(BlogUserInfo.class, metadata);
    }

}

