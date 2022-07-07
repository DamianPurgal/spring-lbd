package lbd.fissst.springlbd.service.exception;

public class SprintNotValidException extends RuntimeException{

    protected final String message;

    public SprintNotValidException(String message){
        super(message);
        this.message = message;
    }
}
