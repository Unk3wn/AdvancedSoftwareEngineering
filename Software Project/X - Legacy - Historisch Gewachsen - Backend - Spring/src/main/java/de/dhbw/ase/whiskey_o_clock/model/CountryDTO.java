package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

// DTO PATTERN

@Data
@AllArgsConstructor
public class CountryDTO implements Serializable {
    private final String countryAbbreviation;
    private final String countryName;
}
