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
@Table(name = "country")
public class Country {

    public Country(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "abbreviation",length = 3)
    private String abbreviation;

    @Column(name = "name")
    private String name;

}
