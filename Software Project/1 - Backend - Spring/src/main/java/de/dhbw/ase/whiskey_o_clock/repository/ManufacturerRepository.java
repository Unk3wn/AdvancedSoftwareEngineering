package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    Manufacturer getManufacturerByName(String name);

    void deleteByName(String name);

}
