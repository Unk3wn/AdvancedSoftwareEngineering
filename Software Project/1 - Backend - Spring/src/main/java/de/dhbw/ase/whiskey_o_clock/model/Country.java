package de.dhbw.ase.whiskey_o_clock.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.util.Objects;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "abbreviation", length = 3, unique = true)
    private String abbreviation;
    @Column(name = "name")
    private String name;


    public Country(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        if (abbreviation.matches("[a-zA-Z]{1,3}")) {
            this.abbreviation = abbreviation;
            return;
        }
        throw new ValidationException("Abbreviation not valid, only between 1 and 3 are Valid!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Country country = (Country) o;
        return uuid != null && Objects.equals(uuid, country.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", name, abbreviation);
    }

}
