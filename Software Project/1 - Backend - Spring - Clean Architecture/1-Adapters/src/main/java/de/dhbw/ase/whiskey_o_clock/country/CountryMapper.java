package de.dhbw.ase.whiskey_o_clock.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;

public class CountryMapper {

    public CountryMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    public static CountryDTO convertCountryToDTO(Country country) {
        return new CountryDTO(country.getUuid(),country.getAbbreviation(), country.getName());
    }

    public static Country convertDTOToCountry(CountryDTO countryDTO) {
        return new Country(countryDTO.getCountryAbbreviation(), countryDTO.getCountryName());
    }

}
