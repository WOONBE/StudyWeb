package StudyWeb.domain;

import StudyWeb.domain.post.Post;
import StudyWeb.status.GroupStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("S")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyGroup extends Post {
    @Enumerated(EnumType.STRING)
    private GroupStatus groupStatus;

    @Builder
    public StudyGroup(Long id, User user, String title,
                      String content, Long hit,
                      List<Comment> comments,
                      List<PostTag> tags, GroupStatus groupStatus) {
        super(id, user, title, content, hit, comments,tags);
        this.groupStatus = groupStatus;
    }

    public void updateStatus(GroupStatus studyStatus) {
        this.groupStatus = groupStatus;
    }


}
