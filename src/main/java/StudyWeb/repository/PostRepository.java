package StudyWeb.repository;

import StudyWeb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Post> findAllDesc();
}
