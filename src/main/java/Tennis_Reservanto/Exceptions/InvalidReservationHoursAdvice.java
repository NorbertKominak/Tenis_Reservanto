package Tennis_Reservanto.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidReservationHoursAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidReservationHoursException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String InvalidReservationHoursHandler(InvalidReservationHoursException ex) {
        return ex.getMessage();
    }
}
