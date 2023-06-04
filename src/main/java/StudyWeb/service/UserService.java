package StudyWeb.service;

import StudyWeb.config.auth.token.RefreshToken;
import StudyWeb.config.auth.token.TokenService;
import StudyWeb.controller.user.UserDTO;
import StudyWeb.domain.User;
import StudyWeb.exception.*;
import StudyWeb.repository.RefreshTokenRepository;
import StudyWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;
    private final EmailService emailService;

    @Transactional
    public User createUserBeforeEmailValidation(final String email) throws Exception {
        String authKey = emailService.createKey();
        log.info("Email authKey = {}", authKey);
        emailService.sendValidationMail(email, authKey);
        User user = User.builder()
                .email(email)
                .emailAuthKey(authKey)
                .emailConfirm(false)
                .build();
        log.info("Crate New User Id : {}, Email : {}", user.getId(), user.getEmail());
        return userRepository.save(user);
    }

    public boolean isEmailExists(final String email) {
        return userRepository.existsByEmail(email);
    }

    public User getByEmail(final String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if (!user.isEmailConfirm()) {
            log.warn("이메일 인증이 완료되지 않은 이메일입니다.");
        }
        return user;
    }

    //Dirty Checking
    @Transactional
    public void updateUserAuthKey(final String email, final String authKey) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.updateAuthKey(authKey);
    }

    /*
     * 극악의 확률로 동일한 확률의 AuthKey가 생성된 경우 예외처리
     * */
    public User getByAuthKey(final String authKey) {
        return userRepository.findByEmailAuthKey(authKey).orElseThrow(EmailAuthKeyNotEqualException::new);
    }

    @Transactional
    public void updateUserConfirm(final String authKey) {
        User user = getByAuthKey(authKey);
        user.updateEmailConfirm(true);
    }

    // 회원가입 마지막 절차 username,password 정보 기입
    // Dirty Checking으로 변경
    @Transactional
    public User createUserAfterEmailValidation(final StudyWeb.controller.user.UserDTO userDto) {
        User user = getByEmail(userDto.getEmail());
        if (!user.isEmailConfirm()) {
            throw new EmailConfirmNotCompleteException();
        }
        checkDupUsername(userDto.getUsername());
        user.updateUserInfo(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()));
        return user;
    }

    private void checkDupUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            log.error("이미 존재하는 username = {}",username);
            throw new IllegalStateException("이미 존재하는 username입니다.");
        }
    }

    public User getByCredentials(final String email, final String password) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if (!user.isEmailConfirm()) {
            throw new EmailConfirmNotCompleteException();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotSameException();
        }
        return user;
    }

    @Transactional
    public StudyWeb.controller.user.UserDTO loginProcess(StudyWeb.controller.user.UserDTO userDTO, User user, HttpServletResponse response) {
        String accessToken = tokenService.createAccessToken(userDTO.getEmail());
        String refreshToken = tokenService.createRefreshToken(userDTO.getEmail());
        RefreshToken token = RefreshToken.builder()
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(token);
        ResponseCookie cookie = cookieService.createCookie("X-AUTH-REFRESH-TOKEN", refreshToken);
        response.setHeader("X-AUTH-ACCESS-TOKEN", accessToken);
        response.setHeader("Set-Cookie", cookie.toString());
        return StudyWeb.controller.user.UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void logoutProcess(HttpServletRequest request, HttpServletResponse response) {
        deleteRefreshCookie(request, response);
    }

    private void deleteRefreshCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie refreshCookie = cookieService.getCookie(request, "X-AUTH-REFRESH-TOKEN");
        if (refreshCookie != null) {
            String refreshToken = refreshCookie.getValue();
            log.info("refreshToken: {}", refreshToken);
            RefreshToken dbRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
            if (dbRefreshToken != null) {
                refreshTokenRepository.delete(dbRefreshToken);
            }
        }
        ResponseCookie deletedCookie = cookieService.deleteCookie("X-AUTH-REFRESH-TOKEN");
        response.setHeader("Set-Cookie", deletedCookie.toString());
    }

    /*
     * 비밀번호 변경 메소드
     * */
    @Transactional
    public void changePassword(String email,String password) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("User {} 's before password was {}",user.getUsername(),user.getPassword());
        if (passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordDupException();
        }
        user.changePassword(passwordEncoder.encode(password));
        log.info("User {} 's password was changed to {}",user.getUsername(),user.getPassword());
    }

    /*
     * Admin 페이지에서 사용
     * */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void deleteUser(String username, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        user.getPosts().clear();
        deleteRefreshCookie(request, response);
    }

    /*
     * Admin 페이지에서 사용
     * */
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        user.getPosts().clear();
    }

    @Transactional
    public UserDTO updateUsername(String before, String username) {
        User user = userRepository.findByUsername(before).orElseThrow(UserNotFoundException::new);
        user.changeUsername(username);
        log.info("username was changed {} to {}",before,username);
        return UserDTO.builder()
                .username(username)
                .email(user.getEmail())
                .build();
    }

    /*
     * Admin 페이지에서 사용
     * */
    @Transactional
    public User createUserByAdmin(StudyWeb.admin.UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .emailConfirm(userDTO.isEmailValidation())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }

    /*
     * Admin 페이지에서 사용
     * */
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    //결제 기능 사용 후에 서비스 이용하도록 변경

}