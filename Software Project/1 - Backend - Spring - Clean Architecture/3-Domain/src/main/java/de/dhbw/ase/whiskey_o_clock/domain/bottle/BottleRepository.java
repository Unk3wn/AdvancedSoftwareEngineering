package de.dhbw.ase.whiskey_o_clock.domain.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;

import java.util.List;
import java.util.UUID;

public interface BottleRepository {
    /** CREATE **/
    Bottle save(Bottle bottle);

    /** READ **/
    List<Bottle> findAll();
    List<Bottle> getBottlesByLabel(String bottleLabel);
    Bottle getFirstBottleByLabelAndManufacturer(String bottleLabel, Manufacturer manufacturer);
    List<Bottle> getBottlesByLabelAndManufacturer(String bottleLabel, Manufacturer manufacturer);
    List<Bottle> getBottlesBySeries(Series series);
    Bottle getBottleByUuid(UUID bottleUUID);
    boolean existsById(UUID bottleUUID);
    boolean existsByLabel(String bottleLabel);
    boolean existsByLabelAndManufacturer(String bottleLabel,Manufacturer manufacturer);

    /** UPDATE **/

    /** DELETE **/
    void delete(Bottle bottleByUUID);

}
