package StudyWeb.exception;

public class PasswordDupException extends BusinessException{
    public PasswordDupException() {
        super(Messages.DUP_PASSWORD);
    }
}