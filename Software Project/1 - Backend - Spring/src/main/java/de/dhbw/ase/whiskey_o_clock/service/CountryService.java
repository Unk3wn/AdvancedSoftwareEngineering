package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    Country saveCountry(final CountryDTO country);
    Country saveCountry(String abbreviation, String name);

    Country updateCountry(UUID countryUUID, CountryDTO countryDTO);

    void deleteCountry(String abbreviation);

    Country getCountryByAbbreviation(String abbreviation);

    List<Country> getAllCountrys();

}
