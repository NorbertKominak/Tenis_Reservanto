package Tennis_Reservanto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


/**
 * This class loads testing data into database.
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ReservationRepository reservationRepository,
                                   CourtRepository courtRepository,
                                   CustomerRepository customerRepository,
                                   SurfaceRepository surfaceRepository) {
        return args -> {
            log.info("Started preloading test data");
            Surface grassSurface = new Surface("grass", 1);
            Surface claySurface = new Surface("clay", 2);
            surfaceRepository.save(grassSurface);
            surfaceRepository.save(claySurface);

            Court firstCourt = new Court(grassSurface);
            Court secondCourt = new Court(claySurface);
            courtRepository.save(firstCourt);
            courtRepository.save(secondCourt);

            Customer firstCustomer = new Customer("0917820320", "Pala");
            Customer secondCustomer = new Customer("0911369334", "Cinka");
            customerRepository.save(firstCustomer);
            customerRepository.save(secondCustomer);

            Reservation firstReservation = new Reservation(
                    true, LocalDate.now(), (byte)7,
                    (byte)10, firstCustomer, firstCourt);
            Reservation secondReservation = new Reservation(
                    false, LocalDate.now(), (byte)7,
                    (byte)10, secondCustomer, secondCourt);
            reservationRepository.save(firstReservation);
            reservationRepository.save(secondReservation);
            log.info("Test data preloading finished");
        };
    }
}
