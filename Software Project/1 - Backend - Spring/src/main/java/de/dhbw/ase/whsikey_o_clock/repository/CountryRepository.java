package de.dhbw.ase.whsikey_o_clock.repository;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface CountryRepository extends JpaRepository<Country, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    Country findCountryByAbbreviation(String abbreviation);

    void deleteByAbbreviation(String abbreviation);
}
