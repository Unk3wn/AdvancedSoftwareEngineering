package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// DTO PATTERN

@Data
@AllArgsConstructor
public class CountryDTO {
    private String abbreviation;
    private String name;
}
