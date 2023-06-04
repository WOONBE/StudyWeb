package StudyWeb.exception;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException() {
        super(Messages.NO_POST);
    }
}