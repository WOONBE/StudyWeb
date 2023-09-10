package StudyWeb.exception;

public class EmailAuthKeyNotEqualException extends BusinessException {
    public EmailAuthKeyNotEqualException() {
        super(Messages.WRONG_AUTH_KEY);
    }
}