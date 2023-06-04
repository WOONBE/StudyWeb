package StudyWeb.domain.post;

import StudyWeb.domain.Comment;
import StudyWeb.domain.PostTag;
import StudyWeb.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("C")
@NoArgsConstructor
public class Chat extends Post{

    @Builder
    public Chat(Long id, User user, String title,
                String content, Long hit,
                 List<Comment> comments,
                List<PostTag> tags) {
        super(id, user, title, content, hit, comments, tags);
    }
}