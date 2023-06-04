package StudyWeb.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTime is a Querydsl query type for BaseTime
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTime extends EntityPathBase<BaseTime> {

    private static final long serialVersionUID = -588698555L;

    public static final QBaseTime baseTime = new QBaseTime("baseTime");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public QBaseTime(String variable) {
        super(BaseTime.class, forVariable(variable));
    }

    public QBaseTime(Path<? extends BaseTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTime(PathMetadata metadata) {
        super(BaseTime.class, metadata);
    }

}

