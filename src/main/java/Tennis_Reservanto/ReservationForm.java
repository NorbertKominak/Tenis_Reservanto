package Tennis_Reservanto;

import Tennis_Reservanto.Exceptions.InvalidDateFormatException;
import Tennis_Reservanto.Exceptions.InvalidPhoneNumberException;
import Tennis_Reservanto.Exceptions.InvalidReservationHoursException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  ReservationForm is filled before new Reservation can be created. It checks
 *  input data and throws exception if necessary.
 */
public class ReservationForm {
    private final int courtId;
    private final boolean foursome;
    private final String phoneNumber;
    private final String name;
    private final LocalDate date;
    private final byte startHour;
    private final byte endHour;

    public ReservationForm(
            int courtId, boolean foursome, String phoneNumber, String name,
            String date, byte startHour, byte endHour) {

        checkPhoneNumberValid(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.date = parseDate(date);
        checkHours(startHour, endHour);
        this.endHour = endHour;
        this.startHour = startHour;
        this.courtId = courtId;
        this.foursome = foursome;
        this.name = name;
    }

    /**
     * Checks whether startHour and endHour are valid. The startHour must be
     * smaller than endHour and they must fit into <7,19> interval. Courts are
     * open from 7:00 till 19:00 all week. Throws exception if invalid.
     * @param startHour When reservation starts
     * @param endHour When reservation ends
     */
    private void checkHours(byte startHour, byte endHour) {
        if (!(endHour > startHour && startHour >= 7 &&
                startHour <= 18 && endHour <= 19)){
            throw new InvalidReservationHoursException(startHour, endHour);
        }
    }

    /**
     * Checks phoneNumber validity. Number can only contain digits or '+'
     * character and must be between 9 and 14 characters long. Throws exception
     * if invalid.
     * @param phoneNumber
     */
    private void checkPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.matches("[^0-9+]") ||
                phoneNumber.length() > 14 || phoneNumber.length() < 9) {
            throw new InvalidPhoneNumberException(phoneNumber);
        }
    }

    /**
     * Dates must follow MM-dd-yyyy format and must not be older than today.
     * Otherwise, exception is thrown.
     * @param date
     * @return If successful parsed date as LocalDate type
     */
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            throw new InvalidDateFormatException(date);
        }

        if (formattedDate == null || formattedDate.isBefore(LocalDate.now())) {
            throw new InvalidDateFormatException(date);
        }

        return formattedDate;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getCourtId() {
        return courtId;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getFoursome() {
        return foursome;
    }

    public byte getEndHour() {
        return endHour;
    }

    public byte getStartHour() {
        return startHour;
    }
}
