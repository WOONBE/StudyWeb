package StudyWeb.repository;

import StudyWeb.domain.StudyGroup;
import StudyWeb.domain.post.Chat;
import StudyWeb.domain.post.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryExtension {
    Page<StudyGroup> findAllStudies(Pageable pageable, PostSearch postSearch);
    Page<Chat> findAllChats(Pageable pageable, PostSearch postSearch);
    //Page<Question> findAllQuestions(Pageable pageable, PostSearch postSearch);
}