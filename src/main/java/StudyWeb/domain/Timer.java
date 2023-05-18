package StudyWeb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Timer {

    @Id @GeneratedValue
    @Column(name = "timer_id")
    private Long id;

    private String startTime;

    private String endTime;

    private LocalDate date;


    //매핑 필요
    private User userId; //manyToone




}
