package de.dhbw.ase.whiskey_o_clock.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CountryToCountryDTOMapper implements Function<Country, CountryDTO> {

    @Override
    public CountryDTO apply(Country country) {
        return map(country);
    }

    private CountryDTO map(Country country) {
        return new CountryDTO(country.getUuid(),country.getAbbreviation(),country.getName());
    }

}
