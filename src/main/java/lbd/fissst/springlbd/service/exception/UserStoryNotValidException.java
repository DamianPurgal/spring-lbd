package lbd.fissst.springlbd.service.exception;

public class UserStoryNotValidException extends RuntimeException{

    protected final String message;

    public UserStoryNotValidException(String message){
        super(message);
        this.message = message;
    }
}
