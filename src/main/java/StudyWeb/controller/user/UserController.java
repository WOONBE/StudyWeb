package StudyWeb.controller.user;

import StudyWeb.controller.ResponseErrorDto;
import StudyWeb.domain.User;
import StudyWeb.service.EmailService;
import StudyWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    @GetMapping("/")
    private ResponseEntity home() {
        return ResponseEntity.ok().body("홈 테스트");
    }

    // 1st Logic in User create
    @PostMapping("/email")
    private ResponseEntity<?> sendEmail(@RequestBody @Validated UserEmailRequestDto dto,
                                        BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
            }
            String email = dto.getEmail();
            if (userService.isEmailExists(email)) {
                if (userService.getByEmail(email).isEmailConfirm()) {
                    ResponseErrorDto errorDto = ResponseErrorDto.builder()
                            .error("이미 가입 완료된 이메일입니다.")
                            .build();
                    return ResponseEntity.badRequest().body(errorDto);
                }
                String authKey = emailService.createKey();
                emailService.sendValidationMail(email, authKey);
                userService.updateUserAuthKey(email, authKey);
                log.info("Email 재전송 완료 : {}", email);
                ResponseErrorDto errorDto = ResponseErrorDto.builder()
                        .error("이메일 재전송 완료")
                        .build();
                return ResponseEntity.ok().body(errorDto);
            }
            User savedUser = userService.createUserBeforeEmailValidation(email);
            UserDTO userDTO = UserDTO.builder()
                    .email(savedUser.getEmail())
                    .build();
            log.info("Email 전송 완료 : {}", email);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }
    // 2nd logic in create User
    // 회원가입 Form에서 이메일 검증 api => Form Data로 넘어와야함
    @PostMapping("/key")
    private ResponseEntity<?> getKeyFromUser(
            @RequestBody UserKeyRequestDto dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        String postKey = dto.getUserKey();
        try {
            userService.updateUserConfirm(postKey);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    // 3rd logic in Create User
    // 회원가입 => 기본 EmailConfirm = false
    // Json으로 넘어와야함
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userCreateRequestDto) {
        try {
            User updatedUser = userService.createUserAfterEmailValidation(userCreateRequestDto);
            UserDTO userDTO = UserDTO.builder()
                    .email(updatedUser.getEmail())
                    .username(updatedUser.getUsername())
                    .build();
            return ResponseEntity.ok().body(userDTO);

        } catch (Exception e) {
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    // Content-Type : JSON
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        try {
            User user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
            UserDTO responseUserDTO = userService.loginProcess(userDTO, user, response);
            log.info("username : {} -> 로그인 성공", user.getUsername());
            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            log.warn(e.getMessage());
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.logoutProcess(request, response);
            return ResponseEntity.ok().body("로그아웃 완료");
        } catch (Exception e) {
            log.warn(e.getMessage());
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //jwtAuthenticationFilter 한번 탔다가 나감
    @PostMapping("/silent-refresh")
    public ResponseEntity<?> silentRefresh() {
        try {
            log.info("silent - refresh");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.warn(e.getMessage());
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    @GetMapping("/users")
    private ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

}