package StudyWeb.domain;

import StudyWeb.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Table(name = "users",uniqueConstraints =
        {@UniqueConstraint(
                name = "USERNAME_UNIQUE",
                columnNames = {"username"})
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String username;


    private String password;

    private boolean emailConfirm;

    private String emailAuthKey;



    private String category;

    //@OneToOne(fetch = FetchType.LAZY,mappedBy = "mileage")
    private Long mileage;

    //@OneToOne(fetch = FetchType.LAZY,mappedBy = "cash")
    private Long cash;

    @OneToMany(mappedBy = "user")
    private List<Timer> timers = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<StudyGroup> studyGroups = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private Goal goal;

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private JoinList joinList;

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    //<--연관관계 편의 메서드-->//
    public void addPost(Post post) {
        this.posts.add(post);
        if (post.getUser() != this) {
            post.setUser(this);
        }
    }
    public void changeUsername(String username) {
        this.username = username;
    }
    public void changePassword(String password) {
        this.password = password;
    }


    public void updateUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateAuthKey(String emailAuthKey) {
        this.emailAuthKey = emailAuthKey;
    }

    public void updateEmailConfirm(boolean isConfirm) {
        this.emailConfirm = isConfirm;
    }



}
