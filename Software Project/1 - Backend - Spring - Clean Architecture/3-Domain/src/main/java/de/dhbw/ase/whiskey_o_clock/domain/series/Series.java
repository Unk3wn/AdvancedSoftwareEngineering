package de.dhbw.ase.whiskey_o_clock.domain.series;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.series.listener.SeriesListener;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@EntityListeners(SeriesListener.class)
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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "series")
    @JsonIgnoreProperties("series")
    private List<Bottle> bottleList = new LinkedList<>();

    public Series(String label) {
        this.label = label;
        this.bottleList = new LinkedList<>();
    }

    public Series(UUID uuid, String label) {
        this(label);
        this.uuid = uuid;
    }

    public Series(String label, List<Bottle> bottleList) {
        this.label = label;
        this.bottleList = bottleList;
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
