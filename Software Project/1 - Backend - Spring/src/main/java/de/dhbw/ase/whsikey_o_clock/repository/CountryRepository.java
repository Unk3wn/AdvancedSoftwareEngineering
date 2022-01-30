package de.dhbw.ase.whsikey_o_clock.repository;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    // intentionally left blank, see JpaRepository interface definition
}
