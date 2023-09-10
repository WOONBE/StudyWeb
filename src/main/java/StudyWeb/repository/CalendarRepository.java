package StudyWeb.repository;

import StudyWeb.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query(value="select * from Calendar c where c.date = :date and c.user_id = :user_id ", nativeQuery=true)
    List<Calendar> findAllByDate(Long date, Long user_id);
}
