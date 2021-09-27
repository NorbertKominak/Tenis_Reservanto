package Tennis_Reservanto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Surface represents tennis court surface type with its name and price.
 */
@Entity
public class Surface {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double pricePerHour;

    public Surface() {}

    public Surface(String name, float pricePerHour) {
        this.name = name;
        this.pricePerHour = pricePerHour;
    }

    public String getName() {
        return name;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public int getId() {
        return id;
    }
}
