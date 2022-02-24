package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    List<Manufacturer> getManufacturerByName(String name);

    Manufacturer getFirstManufacturerByName(String name);

    Manufacturer getManufacturerByUuid(UUID uuid);

    Boolean existsByName(String name);

    /** UPDATE **/

    /**
     * DELETE
     **/
    void deleteByName(String name);
}
