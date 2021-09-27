package Tennis_Reservanto.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidPhoneNumberAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidPhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String InvalidPhoneNumberHandler(InvalidPhoneNumberException ex) {
        return ex.getMessage();
    }
}
