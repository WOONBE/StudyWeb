package StudyWeb.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import status.CameraStatus;
import status.CompleteStatus;
import status.GoalStatus;
import status.PayStatus;

import javax.persistence.*;
import java.awt.*;

@Entity
@Data
@Table(name = "join_list")
public class JoinList {

    @Id @GeneratedValue
    @Column(name = "join_list_id")
    private Long id;

    //private Long totalMoney;

    //isCamera
    @Enumerated(EnumType.STRING)
    private CameraStatus cameraStatus;

    //isPayed
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    //isCompleted
    @Enumerated(EnumType.STRING)
    private CompleteStatus completeStatus;

    @OneToOne(fetch = FetchType.LAZY)
    private Long mileage;

    @OneToOne(fetch = FetchType.LAZY)
    private Long cash;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Goal goal;




}
