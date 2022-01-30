package de.dhbw.ase.whsikey_o_clock.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "series")
public class Series {

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

    @ElementCollection
    @CollectionTable(name="bottle_series_set")
    private Set<Bottle> bottleList = new HashSet<>();

    public Series(String label){
        this.label = label;
    }

    public void addBottle(Bottle bottle){
        this.bottleList.add(bottle);
    }

}
