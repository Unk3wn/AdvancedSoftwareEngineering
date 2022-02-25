package de.dhbw.ase.whiskey_o_clock.domain.country;

import java.util.List;
import java.util.UUID;

public interface CountryRepository {

    /**
     * CREATE
     **/
    Country save(Country country);

    /**
     * READ
     **/
    List<Country> findAll();

    Country getCountryByAbbreviation(String abbreviation);

    Country getCountryByUuid(UUID countryUUID);

    boolean existsByAbbreviation(String abbreviation);

    boolean existsById(UUID countryUUID);

    /** UPDATE **/

    /**
     * DELETE
     **/
    void deleteByAbbreviation(String abbreviation);

    void deleteById(UUID countryUUID);


}
