package de.dhbw.ase.whsikey_o_clock.model;

import de.dhbw.ase.whsikey_o_clock.model.Listener.BottleListener;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(BottleListener.class)
@Table(name = "bottles")
public class Bottle {

    public Bottle(String label, double price, int yearOfManufacture, Manufacturer manufacturer) {
        this.label = label;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.manufacturer = manufacturer;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "label")
    private String label;

    @Column(name = "price")
    private double price;

    @Column(name = "yearOfManufacture")
    private int yearOfManufacture;

    @ManyToOne
    @JoinColumn(name = "manufacturerID")
    private Manufacturer manufacturer;

    @Column(name = "isForSale")
    private boolean forSale;

    @Column(name = "isFavorite")
    private boolean favorite;

    @Column(name = "isUnsaleable")
    private boolean unsaleable;
}
