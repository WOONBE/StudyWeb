package StudyWeb.controller.post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestCreateDto {
    private String username;
    private String title;
    private String content;

    private List<MultipartFile> images;
    private List<String> tags;
}