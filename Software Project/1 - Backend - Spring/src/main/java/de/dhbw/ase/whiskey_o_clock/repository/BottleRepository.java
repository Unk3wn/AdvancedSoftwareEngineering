package de.dhbw.ase.whiskey_o_clock.repository;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.Series;
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

    List<Bottle> getBottlesBySeries(Series series);

    Bottle getFirstBottleByLabelAndManufacturer(String label, Manufacturer manufacturer);

    Bottle getBottleByUuid(UUID bottleUUID);

    boolean existsByLabel(String label);
    boolean existsByLabelAndManufacturer(String label,Manufacturer manufacturer);

    /** UPDATE **/

    /** DELETE **/

}
