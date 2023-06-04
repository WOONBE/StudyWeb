package StudyWeb.domain.post;

import StudyWeb.status.GroupStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostSearch {
    private String order;//정렬 방식
    private String sentence;//검색 단어
    private List<Long> tagId = new ArrayList<>();//태그 필터링
    private GroupStatus groupStatus;//상태
    //private QuestionStatus questionStatus;//상태
}