package de.dhbw.ase.whiskey_o_clock.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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
    private List<Bottle> bottleList = new LinkedList<>();

    public Series(String label) {
        this.label = label;
        this.bottleList = new LinkedList<>();
    }

    public Series(String label, List<Bottle> bottleList) {
        this.label = label;
        this.bottleList = bottleList;
    }

    public void addBottle(Bottle bottle) {
        this.bottleList.add(bottle);
    }

    public void removeBottle(Bottle bottle){
        this.bottleList.remove(bottle);
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

    public void updateFromDTO(SeriesDTO seriesDTO) {
        if(seriesDTO.getSeriesLabel() != null){
            this.label = seriesDTO.getSeriesLabel();
        }
        if(seriesDTO.getSeriesBottleList() != null){
            this.bottleList = seriesDTO.getSeriesBottleList();
        }
    }
}
