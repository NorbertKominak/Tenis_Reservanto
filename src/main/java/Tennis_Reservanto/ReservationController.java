package Tennis_Reservanto;

import Tennis_Reservanto.Exceptions.CourtNotFoundException;
import Tennis_Reservanto.Exceptions.ReservationTimeTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller defines REST API behaviour related to Reservation.
 */
@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final CourtRepository courtRepository;
    private final CustomerRepository customerRepository;

    public ReservationController(ReservationRepository reservationRepository,
                                 CourtRepository courtRepository,
                                 CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.courtRepository = courtRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * GET request for reservations on a specific court.
     * @param courtId Identifier of a specific court.
     * @return List of reservations on a specific court. If court_id is
     * incorrect returns empty List.
     */
    @GetMapping("/reservationsByCourt/{courtId}")
    public List<Reservation> specificCourtReservations(@PathVariable int courtId) {
        return reservationRepository.findByCourt_Id(courtId);
    }

    /**
     * GET request for reservations with a specific phone number(customer`s
     * unique identifier).
     * @param customerId Customer`s phone number
     * @return List of reservations of a specific customer. If customerId is
     * invalid, returns empty List.
     */
    @GetMapping("/reservationsByCustomer/{customerId}")
    public List<Reservation> specificCustomerReservations(
            @PathVariable String customerId) {
        return reservationRepository.findByCustomer_PhoneNumber(customerId);
    }

    /**
     * POST request for a new reservation. Input data are stored in a
     * ReservationForm. The data are used to fetch court and customer from
     * database and to check the court`s availability. If the data are invalid
     * or the court has been booked already, throws exception.
     * @param reservationForm Form to store input data
     * @return If the data are valid returns Reservation`s price. Otherwise,
     * throws exception.
     */
    @PostMapping("/reservation")
    public ResponseEntity<Double> newReservation(
            @RequestBody ReservationForm reservationForm) {
        Court court = getCourtFromReservationForm(reservationForm);
        Customer customer = getCustomerFromReservationForm(reservationForm);
        //In case of existing customer wanting to change his name on reservation
        customer.setName(reservationForm.getName());
        checkReservationAvailability(reservationForm, court);

        Reservation newReservation = new Reservation(
                reservationForm.getFoursome(), reservationForm.getDate(),
                reservationForm.getStartHour(), reservationForm.getEndHour(),
                customer, court);
        reservationRepository.save(newReservation);

        return new ResponseEntity<Double>(newReservation.getPrice(),
                HttpStatus.CREATED);
    }

    /**
     * Gets Court object described in ReservationForm or throws exception if
     * the court is not found.
     */
    private Court getCourtFromReservationForm(ReservationForm reservationForm) {
        return courtRepository
                .findById(reservationForm.getCourtId())
                .orElseThrow(() ->
                        new CourtNotFoundException(reservationForm.getCourtId()));
    }

    /**
     * Gets Customer object described in ReservationForm or creates a new one
     * if it`s not found and returns it.
     */
    private Customer getCustomerFromReservationForm(ReservationForm reservationForm) {
        return customerRepository
                .findById(reservationForm.getPhoneNumber())
                .orElse(customerRepository
                        .save(new Customer(reservationForm.getPhoneNumber(),
                                reservationForm.getName())));
    }

    /**
     * Checks whether specified court is free on the date and time specified
     * in ReservationForm. If not, throws exception.
     */
    private void checkReservationAvailability(ReservationForm reservationForm,
                                              Court court) {
        List<Reservation> sameDayAndCourtReservations = reservationRepository
                .findByCourt_IdAndDate(court.getId(), reservationForm.getDate());

        for (Reservation storedReservation: sameDayAndCourtReservations) {
            if ((reservationForm.getStartHour() < storedReservation.getStartHour() &&
                    reservationForm.getEndHour() > storedReservation.getStartHour()) ||
                    (reservationForm.getStartHour() >= storedReservation.getStartHour() &&
                            reservationForm.getStartHour() < storedReservation.getEndHour())) {

                throw new ReservationTimeTakenException(reservationForm.getDate(),
                        reservationForm.getStartHour(), reservationForm.getEndHour(),
                        reservationForm.getCourtId());
            }
        }
    }
}
