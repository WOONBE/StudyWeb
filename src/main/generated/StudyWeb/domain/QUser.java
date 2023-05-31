package StudyWeb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1690126766L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final NumberPath<Long> cash = createNumber("cash", Long.class);

    public final StringPath category = createString("category");

    public final StringPath email = createString("email");

    public final StringPath emailAuthKey = createString("emailAuthKey");

    public final BooleanPath emailConfirm = createBoolean("emailConfirm");

    public final QGoal goal;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QJoinList joinList;

    public final NumberPath<Long> mileage = createNumber("mileage", Long.class);

    public final StringPath password = createString("password");

    public final ListPath<Post, QPost> posts = this.<Post, QPost>createList("posts", Post.class, QPost.class, PathInits.DIRECT2);

    public final ListPath<StudyGroup, QStudyGroup> studyGroups = this.<StudyGroup, QStudyGroup>createList("studyGroups", StudyGroup.class, QStudyGroup.class, PathInits.DIRECT2);

    public final ListPath<Timer, QTimer> timers = this.<Timer, QTimer>createList("timers", Timer.class, QTimer.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goal = inits.isInitialized("goal") ? new QGoal(forProperty("goal"), inits.get("goal")) : null;
        this.joinList = inits.isInitialized("joinList") ? new QJoinList(forProperty("joinList"), inits.get("joinList")) : null;
    }

}

