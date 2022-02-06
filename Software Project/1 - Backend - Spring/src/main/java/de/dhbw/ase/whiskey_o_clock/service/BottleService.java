package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.BottleDTO;

import java.util.List;
import java.util.UUID;

public interface BottleService {

    /**
     * CREATE
     **/
    Bottle createBottle(final BottleDTO bottleDTO);

    Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName);

    Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean forSale);

    Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean forSale, boolean favorite);

    Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean forSale, boolean favorite, boolean unsaleable);

    /**
     * READ
     **/
    List<Bottle> getBottles();

    List<Bottle> getBottlesByLabel(String label);

    Bottle getBottleByLabelAndManufacturer(String label, String manufacturer);

    Bottle getBottleByUUID(UUID uuid);

    /**
     * UPDATE
     **/
    Bottle updateBottle(UUID bottleUUID, BottleDTO bottleDTO);

    Bottle updateBottleForSale(UUID bottleUUID, Boolean isForSale);

    Bottle updateBottleFavorite(UUID bottleUUID, Boolean isFavorite);

    Bottle updateBottleUnsaleable(UUID bottleUUID, Boolean isUnsaleable);

    /**
     * DELETE
     **/
    void deleteBottleByUUID(UUID bottleUUID);

}
