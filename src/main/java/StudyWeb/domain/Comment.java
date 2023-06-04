package StudyWeb.domain;

import StudyWeb.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    private boolean deleted;

    private Long groupNum;

    private String parent;
    /*
    * comment 작성 할때만, User and Post 영속성 전이를 통해 Persist
    * */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public void updateContent(String contents) {
        this.contents = contents;
    }

    public void updateGroup(Long group) {
        this.groupNum = group;
    }

    public void updateDeleted() {
        this.deleted = true;
    }

    /*
     * 연관관계 편의 메서드
     * */
    public void changePost(Post post){
        this.post = post;
        this.post.getComments().add(this);
    }
}