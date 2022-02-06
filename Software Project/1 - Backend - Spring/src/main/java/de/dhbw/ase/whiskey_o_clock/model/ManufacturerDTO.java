package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ManufacturerDTO implements Serializable {
    private String manufacturerName;
    private String originCountryAbbreviation;
}
