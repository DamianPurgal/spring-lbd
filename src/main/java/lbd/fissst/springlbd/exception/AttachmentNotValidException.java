package lbd.fissst.springlbd.exception;

import org.springframework.http.HttpStatus;

public class AttachmentNotValidException extends BusinessException{

    public AttachmentNotValidException() { super(HttpStatus.BAD_REQUEST, "Attachment is not valid!"); }

    public AttachmentNotValidException(String message) { super(HttpStatus.BAD_REQUEST, message); }
}
