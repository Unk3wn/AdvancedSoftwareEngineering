package de.dhbw.ase.whiskey_o_clock.domain.manufacturer;

import java.util.List;
import java.util.UUID;

public interface ManufacturerRepository {

    /** CREATE **/
    Manufacturer save(Manufacturer manufacturer);

    /** READ **/
    List<Manufacturer> findAll();
    List<Manufacturer> getManufacturerByName(String manufacturerName);
    Manufacturer getFirstManufacturerByName(String manufacturerName);
    Manufacturer getManufacturerByUuid(UUID manufacturerUUID);
    boolean existsByName(String manufacturerName);
    boolean existsById(UUID manufacturerUUID);

    /** UPDATE **/

    /** DELETE **/
    void deleteByName(String manufacturerName);
    void deleteById(UUID manufacturerUUID);


}
