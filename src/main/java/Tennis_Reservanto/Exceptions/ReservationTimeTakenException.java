package Tennis_Reservanto.Exceptions;

import java.time.LocalDate;

public class ReservationTimeTakenException extends RuntimeException{
    public ReservationTimeTakenException(LocalDate date, byte startHour, byte endHour, int court_id) {
        super("Your reservation time " + date + " from " + startHour + ":00 till "
                + endHour + ":00 on court " + court_id + " is unfortunately full.\n");
    }
}
