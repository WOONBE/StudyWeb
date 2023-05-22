package StudyWeb.service;

import StudyWeb.domain.User;
import StudyWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){

        userRepository.save(user);
        return user.getId();

    }

    //유저 중복 검사
    private void validateDuplicateUser(User user){
        Optional<User> findUsers = userRepository.findById(user.getId());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //전체 유저 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }








}
