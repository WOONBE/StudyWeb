package StudyWeb.repository;

import StudyWeb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
//    List<Post> findAllDesc();


    Optional<List<Post>> findAllByUserId(Long id);
}
