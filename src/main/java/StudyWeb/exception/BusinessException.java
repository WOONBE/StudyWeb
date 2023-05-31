package StudyWeb.exception;

public class BusinessException extends RuntimeException{
    protected BusinessException(String message) {
        super(message);
    }
}