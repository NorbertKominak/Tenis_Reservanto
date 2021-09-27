package Tennis_Reservanto.Exceptions;

public class CourtNotFoundException extends RuntimeException {

    public CourtNotFoundException(int id) {
        super("Could not find court with id " + id + "\n");
    }
}
