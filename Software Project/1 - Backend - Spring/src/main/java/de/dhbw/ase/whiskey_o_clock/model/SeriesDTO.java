package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class SeriesDTO {

    private String label;
    private Set<BottleDTO> bottleList = new HashSet<>();
}
