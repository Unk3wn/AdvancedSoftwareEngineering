package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeriesDTO {

    private String seriesLabel;
    private List<BottleDTO> seriesBottleList;
}
