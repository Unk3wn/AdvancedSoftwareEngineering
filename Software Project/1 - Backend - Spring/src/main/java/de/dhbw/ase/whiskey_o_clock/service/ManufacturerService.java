package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    Manufacturer createManufacturer(String name, String countryAbbreviation);

    void deleteManufacturerByName(String name);

    Manufacturer getManufacturerByName(String name);

    List<Manufacturer> getAllManufacturers();

}
