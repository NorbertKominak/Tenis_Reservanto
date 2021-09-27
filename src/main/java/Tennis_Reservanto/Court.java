package Tennis_Reservanto;

import javax.persistence.*;

/**
 * Court represents tennis court available in the reservation system.
 * Various courts can have various surfaces.
 */
@Entity
public class Court {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name="SURFACE_ID", nullable = false)
    private Surface surface;

    public Court() {}

    public Court(Surface surface) {
        this.surface = surface;
    }

    public int getId() {
        return id;
    }

    public Surface getSurface() {
        return surface;
    }
}
