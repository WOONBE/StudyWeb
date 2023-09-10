package StudyWeb.exception;

public class UserNotMatchException extends BusinessException{
    public UserNotMatchException() {
        super(Messages.USER_NOT_MATCH);
    }
}