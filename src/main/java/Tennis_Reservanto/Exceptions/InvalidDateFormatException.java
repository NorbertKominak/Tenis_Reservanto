package Tennis_Reservanto.Exceptions;

import java.time.LocalDate;

public class InvalidDateFormatException extends RuntimeException{
    public InvalidDateFormatException(String invalidDate) {
        super("Provided date: " + invalidDate + ", expected date format is MM-dd-yyyy." +
                " Only dates after " + LocalDate.now() + " are valid.\n");
    }
}
