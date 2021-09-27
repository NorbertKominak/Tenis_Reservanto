package Tennis_Reservanto;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findByCourt_Id(int id);
    public List<Reservation> findByCourt_IdAndDate(int id, LocalDate date);
    public List<Reservation> findByCustomer_PhoneNumber(String phoneNumber);
}
