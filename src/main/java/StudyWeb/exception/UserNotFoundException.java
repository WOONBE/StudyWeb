package StudyWeb.exception;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(Messages.NO_USER_MESSAGE);
    }
}