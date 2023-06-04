package StudyWeb.controller.user;

import StudyWeb.controller.validation.EmailCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailRequestDto {
    @EmailCheck
    private String email;
}