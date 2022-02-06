package de.dhbw.ase.whiskey_o_clock.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
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
    @CollectionTable(name = "bottle_series_set")
    private Set<Bottle> bottleList = new HashSet<>();

    public Series(String label) {
        this.label = label;
    }

    public void addBottle(Bottle bottle) {
        this.bottleList.add(bottle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Series series = (Series) o;
        return uuid != null && Objects.equals(uuid, series.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
