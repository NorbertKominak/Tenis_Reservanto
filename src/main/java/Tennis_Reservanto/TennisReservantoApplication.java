package Tennis_Reservanto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Server app to manage tennis club`s courts reservations. It has four REST
 * Endpoints described in CourtController and ReservationController classes.
 */
@SpringBootApplication
public class TennisReservantoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisReservantoApplication.class, args);
	}

}
