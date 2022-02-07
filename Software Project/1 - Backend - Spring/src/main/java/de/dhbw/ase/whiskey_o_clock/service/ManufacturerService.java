package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.ManufacturerDTO;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {

    /**
     * CREATE
     **/
    Manufacturer createManufacturer(ManufacturerDTO manufacturerDTO);

    Manufacturer createManufacturer(String name, String countryAbbreviation);


    /**
     * READ
     **/
    Manufacturer getManufacturerByName(String name);

    List<Manufacturer> getAllManufacturers();

    /**
     * UPDATE
     **/
    Manufacturer updateManufacturer(UUID manufacturerUUID, ManufacturerDTO manufacturerDTO);

    /**
     * DELETE
     **/
    void deleteManufacturerByUUID(UUID uuid);


}
