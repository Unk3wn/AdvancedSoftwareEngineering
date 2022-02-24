package de.dhbw.ase.plugins.persistence.hibernate.manufacturer;

import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /** READ **/
    List<Manufacturer> getManufacturerByName(String name);

    Manufacturer getFirstManufacturerByName(String name);

    Manufacturer getManufacturerByUuid(UUID uuid);

    Boolean existsByName(String name);

    /** UPDATE **/

    /** DELETE **/
    void deleteByName(String name);
}
