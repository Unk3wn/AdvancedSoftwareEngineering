package de.dhbw.ase.whiskey_o_clock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BottleDTO implements Serializable {

    private String label;
    private Double price;
    private Integer yearOfManufacture;
    private UUID manufacturer;
}
