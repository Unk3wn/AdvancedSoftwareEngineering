package de.dhbw.ase.whiskey_o_clock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.whiskey_o_clock.model.listener.BottleListener;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(BottleListener.class)
@Table(name = "bottles")
public class Bottle {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "label",nullable = false)
    private String label;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "yearOfManufacture",nullable = false)
    private int yearOfManufacture;
    @ManyToOne
    @JoinColumn(name = "manufacturerID",nullable = false)
    private Manufacturer manufacturer;
    @Column(name = "isForSale")
    private boolean forSale;
    @Column(name = "isFavorite")
    private boolean favorite;
    @Column(name = "isUnsaleable")
    private boolean unsaleable;
    @ManyToOne
    @JsonIgnoreProperties("bottleList")
    @JoinColumn(name="series_id")
    private Series series;

    public Bottle(String label, double price, int yearOfManufacture, Manufacturer manufacturer) {
        this(label,price,yearOfManufacture,manufacturer,null);
    }

    public Bottle(String label, double price, int yearOfManufacture, Manufacturer manufacturer,Series series) {
        this(label, price, yearOfManufacture, manufacturer, series, false, false,false);
    }

    public Bottle(String label, double price, int yearOfManufacture, Manufacturer manufacturer, Series series,boolean isForSale, boolean isFavorite, boolean isUnsaleable) {
        this.label = label;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.manufacturer = manufacturer;
        this.forSale = isForSale;
        this.favorite = isFavorite;
        this.unsaleable = isUnsaleable;
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bottle bottle = (Bottle) o;
        return uuid != null && Objects.equals(uuid, bottle.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
