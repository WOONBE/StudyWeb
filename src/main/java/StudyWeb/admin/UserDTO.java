package StudyWeb.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private boolean emailValidation;
    private String password;

    private int chatSize;
    private int studiesSize;
    private int questionsSize;
    private int receivedLikes;
    private int receivedComments;
}