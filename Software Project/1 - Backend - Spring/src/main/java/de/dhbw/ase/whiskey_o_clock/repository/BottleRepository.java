package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BottleRepository extends JpaRepository<Bottle, UUID> {
    // intentionally left blank, see JpaRepository interface definition
}
