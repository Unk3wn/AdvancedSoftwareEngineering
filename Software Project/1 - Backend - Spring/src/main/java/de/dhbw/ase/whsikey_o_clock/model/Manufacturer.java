package de.dhbw.ase.whsikey_o_clock.model;

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
@Table(name = "manufacturer")
public class Manufacturer {

    public Manufacturer(String name, Country originCountry) {
        this.name = name;
        this.originCountry = originCountry;
    }

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

}
