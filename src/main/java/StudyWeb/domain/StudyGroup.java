package StudyWeb.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "study_group")
public class StudyGroup {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    private String groupIntro;

    // maxNum, curNum enum으로 삽입예정
    private String category;

    //관리자 아이디(매핑 필ㅇ)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;





}
