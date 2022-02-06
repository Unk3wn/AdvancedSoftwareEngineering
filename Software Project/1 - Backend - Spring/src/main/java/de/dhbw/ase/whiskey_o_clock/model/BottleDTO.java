package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BottleDTO implements Serializable {

    private String label;
    private double price;
    private int yearOfManufacture;
    private String manufacturerName;
}
