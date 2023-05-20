package StudyWeb.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Goal {

    @Id @GeneratedValue
    @Column(name = "goal_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime goalDate;

    private String totalMoney;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "goal")
    private JoinList joinList;

}

