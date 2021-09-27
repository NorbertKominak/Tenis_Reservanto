package Tennis_Reservanto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_PHONE_NUMBER", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="COURT_ID", nullable = false)
    private Court court;
    private boolean foursome;
    private LocalDate date;
    private byte startHour;
    private byte endHour;

    public Reservation() {}

    public Reservation(boolean foursome, LocalDate date, byte startHour,
                       byte endHour, Customer customer, Court court) {
        this.endHour = endHour;
        this.startHour = startHour;
        this.foursome = foursome;
        this.date = date;
        this.customer = customer;
        this.court = court;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Court getCourt() {
        return court;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getFoursome() {
        return foursome;
    }

    public double getPrice() {
        double price = court.getSurface().getPricePerHour() * (endHour - startHour);
        return foursome ? price * 1.5 : price;
    }

    public byte getEndHour() {
        return endHour;
    }

    public byte getStartHour() {
        return startHour;
    }
}
