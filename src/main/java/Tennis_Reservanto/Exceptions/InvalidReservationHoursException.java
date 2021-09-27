package Tennis_Reservanto.Exceptions;


public class InvalidReservationHoursException extends RuntimeException{
    public InvalidReservationHoursException(byte startHour, byte endHour) {
        super("You cannot reserve from " + startHour + ":00 till " + endHour + ":00. " +
                "Opening hours are from 7:00 till 19:00 all week\n");
    }
}
