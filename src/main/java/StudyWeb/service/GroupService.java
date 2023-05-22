package StudyWeb.service;

import StudyWeb.domain.StudyGroup;
import StudyWeb.domain.User;
import StudyWeb.repository.GroupRepository;
import StudyWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.security.PrivateKey;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    //일단 전체적으로 보류
    private final GroupRepository groupRepository;

    private final User user;
    private final UserRepository userRepository;
    @Transactional
    public Long joinGroup(StudyGroup studyGroup,User user){

        Optional<User> joinUser = userRepository.findById(user.getId());

        return user.getId();
    }


}
