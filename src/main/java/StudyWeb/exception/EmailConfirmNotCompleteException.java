package StudyWeb.exception;

public class EmailConfirmNotCompleteException extends BusinessException {
    public EmailConfirmNotCompleteException() {
        super(Messages.NO_EMAIL_CONFIRM);
    }
}