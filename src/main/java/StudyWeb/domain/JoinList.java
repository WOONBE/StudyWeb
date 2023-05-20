package StudyWeb.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
@Table(name = "join_list")
public class JoinList {

    @Id @GeneratedValue
    @Column(name = "join_list_id")
    private Long id;

    //isCamera
    //isPayed
    //isCompleted

    private Long mileage;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Goal goal;


}
