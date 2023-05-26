package StudyWeb.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String phone;

    private String email;

    private String category;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "mileage")
    private Long mileage;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "cash")
    private Long cash;

    @OneToMany(mappedBy = "user")
    private List<Timer> timers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<StudyGroup> studyGroups = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private Goal goal;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private JoinList joinList;







}
