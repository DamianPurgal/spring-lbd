package lbd.fissst.springlbd.exception;

import org.springframework.http.HttpStatus;

public class UserStoryNotValidException extends BusinessException{

    public UserStoryNotValidException() { super(HttpStatus.BAD_REQUEST, "UserStory is not valid!"); }

    public UserStoryNotValidException(String message) { super(HttpStatus.BAD_REQUEST, message); }

}
