package StudyWeb.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 709796679L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final StudyWeb.domain.QBaseTime _super = new StudyWeb.domain.QBaseTime(this);

    public final ListPath<StudyWeb.domain.Comment, StudyWeb.domain.QComment> comments = this.<StudyWeb.domain.Comment, StudyWeb.domain.QComment>createList("comments", StudyWeb.domain.Comment.class, StudyWeb.domain.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> hit = createNumber("hit", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final ListPath<StudyWeb.domain.PostTag, StudyWeb.domain.QPostTag> postTags = this.<StudyWeb.domain.PostTag, StudyWeb.domain.QPostTag>createList("postTags", StudyWeb.domain.PostTag.class, StudyWeb.domain.QPostTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final StudyWeb.domain.QUser user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new StudyWeb.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

