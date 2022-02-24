package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ManufacturerDTO implements Serializable {
    private final String manufacturerName;
    private final String originCountryAbbreviation;
}
