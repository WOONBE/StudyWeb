package StudyWeb.exception;

public class OrderNotFoundException extends BusinessException{
    public OrderNotFoundException() {
        super(Messages.OREDER_NOT_FOUND);
    }
}