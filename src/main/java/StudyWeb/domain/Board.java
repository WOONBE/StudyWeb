package StudyWeb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;


    //매핑 필요
    private String name;

    //매핑필요
    private StudyGroup groupId;
}
