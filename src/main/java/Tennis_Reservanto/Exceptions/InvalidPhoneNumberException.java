package Tennis_Reservanto.Exceptions;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(String phoneNumber) {
        super("Phone number: " + phoneNumber + ", is invalid. " +
                "Phone number can only include digits or '+' character " +
                "and must be between 9 to 14 characters long.\n");
    }
}
