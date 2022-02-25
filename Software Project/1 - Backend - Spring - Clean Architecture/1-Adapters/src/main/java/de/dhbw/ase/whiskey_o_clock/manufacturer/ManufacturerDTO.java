package de.dhbw.ase.whiskey_o_clock.manufacturer;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ManufacturerDTO implements Serializable {
    @JsonProperty("uuid")
    private final UUID uuid;
    @JsonProperty("originCountry")
    private final CountryDTO country;
    @JsonProperty("name")
    private final String name;
}
