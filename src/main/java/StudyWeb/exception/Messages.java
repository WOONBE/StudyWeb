package StudyWeb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Messages {
    public static final String NO_USER_MESSAGE = "존재하지 않는 사용자입니다.";
    public static final String NO_EMAIL_CONFIRM = "이메일 인증이 완료되지 않은 사용자입니다.";
    public static final String PASSWORD_NOT_EQUAL = "비밀번호가 일치하지 않습니다.";
    public static final String ALREADY_EXIST_EMAIL = "이미 존재하는 이메일입니다.";
    public static final String WRONG_AUTH_KEY = "입력한 인증키가 일치하지 않습니다.";
    public static final String NO_POST = "존재하지 않는 게시글입니다.";
    public static final String NO_LIKE = "존재하지 않는 좋아요 엔티티입니다.";
    public static final String DUP_PASSWORD = "이전 비밀번호와 동일합니다.";
    public static final String COMMENT_CONTENT_EMPTY = "댓글의 내용이 없습니다.";
    public static final String COMMENT_NOT_FOUND = "해당 댓글은 존재하지 않습니다.";
    public static final String RECOMMENT_NOT_FOUND = "부모 댓글이 존재하지 않습니다.";
    public static final String TAG_NOT_FOUND = "기존 게시글에 설정된 태그가 아닙니다.";
    public static final String USER_NOT_MATCH = "잘못된 수정 요청입니다.";
    public static final String OREDER_NOT_FOUND = "잘못된 정렬 방식입니다.";
    public static final String POST_NOT_CREATED = "작성된 포스트가 없습니다.";
}
