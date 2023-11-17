package net.javaguides.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailNotFoundException  extends RuntimeException  {
    private String message;

    public EmailNotFoundException(String message){
        super(message);
    }
}
