package StudyWeb.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;


    //매핑 필요
    @ManyToOne
    private String name;

    //매핑필요
    private StudyGroup groupId;
}
