package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface BottleRepository extends JpaRepository<Bottle, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    List<Bottle> getBottlesByLabel(String label);
    List<Bottle> getBottlesByLabelAndManufacturer(String label, Manufacturer manufacturer);
    Bottle getFirstBottleByLabelAndManufacturer(String label, Manufacturer manufacturer);
    Bottle getBottleByUuid(UUID bottleUUID);

    /** UPDATE **/

    /** DELETE **/

}
