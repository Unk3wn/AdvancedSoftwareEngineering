package de.dhbw.ase.whiskey_o_clock.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

// DTO PATTERN

@Data
@AllArgsConstructor
public class CountryDTO implements Serializable {
    @JsonProperty("uuid")
    private final UUID uuid;
    @JsonProperty("abbreviation")
    private final String countryAbbreviation;
    @JsonProperty("name")
    private final String countryName;
}
