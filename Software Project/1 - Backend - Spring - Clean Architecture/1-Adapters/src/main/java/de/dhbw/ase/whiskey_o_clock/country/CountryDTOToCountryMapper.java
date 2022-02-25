package de.dhbw.ase.whiskey_o_clock.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CountryDTOToCountryMapper implements Function<CountryDTO, Country> {

    @Override
    public Country apply(CountryDTO countryDTO) {
        return map(countryDTO);
    }

    private Country map(CountryDTO countryDTO) {
        return new Country(countryDTO.getUuid(),countryDTO.getAbbreviation(), countryDTO.getName());
    }

}
