package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BottleDTO implements Serializable {
    private final String label;
    private final Double price;
    private final Integer yearOfManufacture;
    private final UUID manufacturer;
    private final boolean forSale;
    private final boolean favorite;
    private final boolean unsaleable;
    private final UUID series;
}
