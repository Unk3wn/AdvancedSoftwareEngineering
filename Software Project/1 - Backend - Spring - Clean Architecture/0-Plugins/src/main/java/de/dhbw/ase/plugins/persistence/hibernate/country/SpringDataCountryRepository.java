package de.dhbw.ase.plugins.persistence.hibernate.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCountryRepository extends JpaRepository<Country, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Country getCountryByAbbreviation(String abbreviation);

    Country getCountryByUuid(UUID uuid);

    Boolean existsByAbbreviation(String abbreviation);

    /** UPDATE **/

    /**
     * DELETE
     **/
    void deleteByAbbreviation(String abbreviation);
}
