package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SeriesDTO {

    private String seriesLabel;
    private Set<Bottle> seriesBottleList;
}
