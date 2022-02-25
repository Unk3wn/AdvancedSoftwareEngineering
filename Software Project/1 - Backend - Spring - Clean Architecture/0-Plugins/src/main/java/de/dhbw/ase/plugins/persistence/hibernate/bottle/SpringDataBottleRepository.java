package de.dhbw.ase.plugins.persistence.hibernate.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataBottleRepository extends JpaRepository<Bottle, UUID> {
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

    boolean existsByLabelAndManufacturer(String label, Manufacturer manufacturer);

    /** UPDATE **/

    /** DELETE **/

}
