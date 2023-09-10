package StudyWeb.repository;

import StudyWeb.domain.StudyGroup;
import StudyWeb.domain.User;
import StudyWeb.domain.post.Chat;
import StudyWeb.domain.post.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> ,PostRepositoryExtension{
    Optional<Chat> findChatById(Long id);

   // Optional<Question> findQuestionById(Long id);

    Optional<StudyGroup> findStudyById(Long id);

    //Optional<List<Chat>> findTop3ChatByOrderByHitDesc();

    //Optional<List<StudyGroup>> findTop3StudyByOrderByHitDesc();

    //Optional<List<Question>> findTop3QuestionByOrderByHitDesc();

//    @Query(value = "select p from Post p" +
//            " left join Like l on p.id = l.post.id " +
//            " where Type(p) IN(Chat)" +
//            " group by p.id order by count(l.post.id) desc")
//    Optional<List<Chat>> findTop3ChatByOrderByLikes(Pageable pageable);

    /*
     * @Query를 사용하는데 들고오는 갯수의 제한이 필요한 경우 아래처럼
     * */
//    default Optional<List<Chat>> findTop3ChatByOrderByLikes() {
//        return findTop3ChatByOrderByLikes(PageRequest.of(0,3));
//    }

//    @Query(value = "select p from Post p" +
//            " left join Like l on p.id = l.post.id " +
//            " where Type(p) IN(StudyGroup)" +
//            " group by p.id order by count(l.post.id) desc")
//    Optional<List<StudyGroup>> findTop3StudyByOrderByLikes(Pageable pageable);

//    default Optional<List<StudyGroup>> findTop3StudyByOrderByLikes() {
//        return findTop3StudyByOrderByLikes(PageRequest.of(0,3));
//    }

//    @Query(value = "select p from Post p" +
//            " left join Like l on p.id = l.post.id " +
//            " where Type(p) IN(Question )" +
//            " group by p.id order by count(l.post.id) desc")
//    Optional<List<Question>> findTop3QuestionByOrderByLikes(Pageable pageable);
//
//    default Optional<List<Question>> findTop3QuestionByOrderByLikes() {
//        return findTop3QuestionByOrderByLikes(PageRequest.of(0,3));
//    }

    @Query("select c from Chat c")
    List<Chat> findAllChatsWithoutSorting();

    @Query("select s from StudyGroup s")
    List<StudyGroup> findAllStudiesWithoutSorting();

//    @Query("select q from Question q")
//    List<Question> findAllQuestionsWithoutSorting();

    Optional<List<Chat>> findAllChatsByUser(User user);
    Optional<List<StudyGroup>> findAllStudiesByUser(User user);
    //Optional<List<Question>> findAllQuestionsByUser(User user);


    //select * from post join likes on post.post_id = likes.post_id where likes.user_id = 1;
//    @Query("select c from Chat c join Like l on c.id = l.post.id where l.user.id = :id")
//    Optional<List<Chat>> findAllLikeChatsByUserId(@Param("id")Long id);
//
//    @Query("select s from StudyGroup s join Like l on s.id = l.post.id where l.user.id = :id")
//    Optional<List<StudyGroup>> findAllLikeStudiesByUserId(@Param("id")Long id);

//    @Query("select q from Question q join Like l on q.id = l.post.id where l.user.id = :id")
//    Optional<List<Question>> findAllLikeQuestionsByUserId(@Param("id")Long id);

    Optional<List<Post>> findAllByUserId(Long id);
}