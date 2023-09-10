package StudyWeb.exception;

public class PasswordNotSameException extends BusinessException {
    public PasswordNotSameException() {
        super(Messages.PASSWORD_NOT_EQUAL);
    }
}