package StudyWeb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}

