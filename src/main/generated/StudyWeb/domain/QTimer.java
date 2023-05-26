package StudyWeb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimer is a Querydsl query type for Timer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimer extends EntityPathBase<Timer> {

    private static final long serialVersionUID = -855536226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimer timer = new QTimer("timer");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QTimer(String variable) {
        this(Timer.class, forVariable(variable), INITS);
    }

    public QTimer(Path<? extends Timer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimer(PathMetadata metadata, PathInits inits) {
        this(Timer.class, metadata, inits);
    }

    public QTimer(Class<? extends Timer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

