package Tennis_Reservanto.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CourtNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CourtNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CourtNotFoundHandler(CourtNotFoundException ex) {
        return ex.getMessage();
    }
}
