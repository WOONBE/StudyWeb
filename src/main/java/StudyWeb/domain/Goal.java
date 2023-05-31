package StudyWeb.domain;

import lombok.Data;
import StudyWeb.status.GoalStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goal")
public class Goal {
    //내 목표

    @Id @GeneratedValue
    @Column(name = "goal_id")
    private Long id;

//    private String title;
//
//    private String content;
//
//    private LocalDateTime goalDate;
//
//    private String totalMoney;
    @Enumerated(EnumType.STRING)
    private GoalStatus goalStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "goal")
    private JoinList joinList;

}

