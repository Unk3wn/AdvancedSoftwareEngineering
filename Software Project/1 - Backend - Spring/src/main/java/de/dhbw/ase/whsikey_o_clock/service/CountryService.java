package de.dhbw.ase.whsikey_o_clock.service;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import de.dhbw.ase.whsikey_o_clock.model.CountryDTO;

import java.util.List;

public interface CountryService {

    Country saveCountry(final CountryDTO country);

    Country saveCountry(String abbreviation, String name);

    void deleteCountry(String abbreviation);

    Country getCountryByAbbreviation(String abbreviation);

    List<Country> getAllCountrys();

}
