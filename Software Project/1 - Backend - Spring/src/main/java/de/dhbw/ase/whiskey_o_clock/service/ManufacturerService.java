package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    /** CREATE **/
    Manufacturer createManufacturer(String name, String countryAbbreviation);

    /** READ **/
    Manufacturer getManufacturerByName(String name);
    List<Manufacturer> getAllManufacturers();

    /** UPDATE **/


    /** DELETE **/
    void deleteManufacturerByName(String name);





}
