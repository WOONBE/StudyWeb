package StudyWeb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyGroup is a Querydsl query type for StudyGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyGroup extends EntityPathBase<StudyGroup> {

    private static final long serialVersionUID = 1499903869L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyGroup studyGroup = new QStudyGroup("studyGroup");

    public final StudyWeb.domain.post.QPost _super;

    //inherited
    public final ListPath<Comment, QComment> comments;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt;

    public final EnumPath<StudyWeb.status.GroupStatus> groupStatus = createEnum("groupStatus", StudyWeb.status.GroupStatus.class);

    //inherited
    public final NumberPath<Long> hit;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt;

    //inherited
    public final ListPath<PostTag, QPostTag> postTags;

    //inherited
    public final StringPath title;

    // inherited
    public final QUser user;

    public QStudyGroup(String variable) {
        this(StudyGroup.class, forVariable(variable), INITS);
    }

    public QStudyGroup(Path<? extends StudyGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyGroup(PathMetadata metadata, PathInits inits) {
        this(StudyGroup.class, metadata, inits);
    }

    public QStudyGroup(Class<? extends StudyGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new StudyWeb.domain.post.QPost(type, metadata, inits);
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

