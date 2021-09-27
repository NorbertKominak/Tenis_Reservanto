package Tennis_Reservanto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller defines REST API behaviour related to Court.
 */
@RestController
public class CourtController {

    private final CourtRepository courtRepository;

    public CourtController(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    /**
     * GET request for all courts in the system.
     * @return List of all courts in the system.
     */
    @GetMapping("/courts")
    public List<Court> allCourts() {
        return courtRepository.findAll();
    }
}
