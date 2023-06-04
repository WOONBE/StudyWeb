package StudyWeb.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChat is a Querydsl query type for Chat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChat extends EntityPathBase<Chat> {

    private static final long serialVersionUID = 709402111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChat chat = new QChat("chat");

    public final QPost _super;

    //inherited
    public final ListPath<StudyWeb.domain.Comment, StudyWeb.domain.QComment> comments;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt;

    //inherited
    public final NumberPath<Long> hit;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt;

    //inherited
    public final ListPath<StudyWeb.domain.PostTag, StudyWeb.domain.QPostTag> postTags;

    //inherited
    public final StringPath title;

    // inherited
    public final StudyWeb.domain.QUser user;

    public QChat(String variable) {
        this(Chat.class, forVariable(variable), INITS);
    }

    public QChat(Path<? extends Chat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChat(PathMetadata metadata, PathInits inits) {
        this(Chat.class, metadata, inits);
    }

    public QChat(Class<? extends Chat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QPost(type, metadata, inits);
        this.comments = _super.comments;
        this.content = _super.content;
        this.createAt = _super.createAt;
        this.hit = _super.hit;
        this.id = _super.id;
        this.lastModifiedAt = _super.lastModifiedAt;
        this.postTags = _super.postTags;
        this.title = _super.title;
        this.user = _super.user;
    }

}

