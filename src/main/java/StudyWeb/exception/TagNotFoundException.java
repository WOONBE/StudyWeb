package StudyWeb.exception;

public class TagNotFoundException extends BusinessException{
    public TagNotFoundException() {
        super(Messages.TAG_NOT_FOUND);
    }
}