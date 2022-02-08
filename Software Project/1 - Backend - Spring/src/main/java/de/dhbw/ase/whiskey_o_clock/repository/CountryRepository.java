package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface CountryRepository extends JpaRepository<Country, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Country getCountryByAbbreviation(String abbreviation);

    Country getCountryByUuid(UUID uuid);

    /** UPDATE **/

    /**
     * DELETE
     **/
    void deleteByAbbreviation(String abbreviation);
}
