package StudyWeb.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String category;

    @OneToMany(mappedBy = "user")
    private List<Timer> timers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<StudyGroup> studyGroups = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Goal goal;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private JoinList joinList;







}
