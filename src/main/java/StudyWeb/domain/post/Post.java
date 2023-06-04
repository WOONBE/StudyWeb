package StudyWeb.domain.post;

import StudyWeb.controller.post.PostRequestUpdateDto;
import StudyWeb.domain.BaseTime;
import StudyWeb.domain.Comment;
import StudyWeb.domain.PostTag;
import StudyWeb.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Column(name = "hit_count")
    private Long hit;

//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();


    //==비지니스 로직==//
    public void plusHit() {
        this.hit++;
    }

    public void updatePost(PostRequestUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
    }

//    public void addImage(Image image) {
//        this.images.add(image);
//        image.setPost(this);
//    }

    //<--연관관계 편의 메서드-->//
    public void setUser(User user) {
        if (this.user != null) {
            this.user.getPosts().remove(this);
        }
        this.user = user;
        user.getPosts().add(this);
    }
}