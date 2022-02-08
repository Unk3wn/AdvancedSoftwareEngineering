package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    /**
     * CREATE
     **/
    Country saveCountry(final CountryDTO country);

    Country saveCountry(String abbreviation, String name);

    /**
     * READ
     **/
    List<Country> getAllCountrys();

    Country getCountryByAbbreviation(String abbreviation);

    /**
     * UPDATE
     **/
    Country updateCountry(UUID countryUUID, CountryDTO countryDTO);

    /**
     * DELETE
     **/
    void deleteCountry(String abbreviation);

    void deleteCountry(UUID uuid);


}
