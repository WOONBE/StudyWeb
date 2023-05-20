package StudyWeb.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "timer")
public class Timer {

    @Id @GeneratedValue
    @Column(name = "timer_id")
    private Long id;

    private String startTime;

    private String endTime;

    private LocalDate date;


    //매핑 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //사용자 id




}
