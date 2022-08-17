package lbd.fissst.springlbd.exception;

import org.springframework.http.HttpStatus;

public class SprintNotValidException extends BusinessException{

    public SprintNotValidException() { super(HttpStatus.BAD_REQUEST, "Sprint is not valid!"); }

    public SprintNotValidException(String message) { super(HttpStatus.BAD_REQUEST, message); }

}
