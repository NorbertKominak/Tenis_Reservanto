package Tennis_Reservanto;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class serves as entity to store data about customers in the reservation
 * system. Each customer phoneNumber is unique identifier. His name is stored
 * as well.
 */
@Entity
public class Customer {
    private @Id String phoneNumber;
    private String name;

    public Customer() {}

    public Customer(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
}
