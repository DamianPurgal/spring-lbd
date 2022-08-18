package lbd.fissst.springlbd.exception;

import org.springframework.http.HttpStatus;

public class AppEntityNotFoundException extends BusinessException{

    public AppEntityNotFoundException() { super(HttpStatus.NOT_FOUND, "Entity not found!"); }

    public AppEntityNotFoundException(String message) { super(HttpStatus.NOT_FOUND, message); }
}
