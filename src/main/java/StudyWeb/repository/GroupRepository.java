package StudyWeb.repository;

import StudyWeb.domain.StudyGroup;
import StudyWeb.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;



@Repository
public interface GroupRepository extends JpaRepository<StudyGroup, Long> {







}
