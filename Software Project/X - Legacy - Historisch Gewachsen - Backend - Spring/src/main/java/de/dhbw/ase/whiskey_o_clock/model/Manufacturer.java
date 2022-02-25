package de.dhbw.ase.whiskey_o_clock.model;

import de.dhbw.ase.whiskey_o_clock.model.listener.ManufacturerListener;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(ManufacturerListener.class)
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "originCountryId")
    private Country originCountry;

    public Manufacturer(String name, Country originCountry) {
        this.name = name;
        this.originCountry = originCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Manufacturer that = (Manufacturer) o;
        return uuid != null && Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
