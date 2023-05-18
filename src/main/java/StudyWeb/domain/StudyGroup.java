package StudyWeb.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class StudyGroup {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    private String groupIntro;

    // maxNum, curNum enum으로 삽입예정
    private String category;

    //관리자 아이디요(매핑 필ㅇ)
    private User userId;





}
