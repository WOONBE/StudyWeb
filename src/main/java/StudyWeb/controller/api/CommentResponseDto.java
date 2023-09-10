package StudyWeb.controller.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDto {
    private String username;
    private String title;
    private Long commentId;
    private String contents;
    private Long group;
    private String parent;
    private boolean deleted;
    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;
}