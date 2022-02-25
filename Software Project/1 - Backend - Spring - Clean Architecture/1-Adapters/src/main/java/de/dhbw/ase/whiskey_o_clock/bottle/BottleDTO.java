package de.dhbw.ase.whiskey_o_clock.bottle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.series.SeriesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BottleDTO implements Serializable {
    @JsonProperty("uuid")
    private final UUID uuid;
    @JsonProperty("label")
    private final String label;
    @JsonProperty("price")
    private final Double price;
    @JsonProperty("yearOfManufacture")
    private final Integer yearOfManufacture;
    @JsonProperty("manufacturer")
    private final ManufacturerDTO manufacturer;
    @JsonProperty("forSale")
    private final boolean forSale;
    @JsonProperty("favorite")
    private final boolean favorite;
    @JsonProperty("unsaleable")
    private final boolean unsaleable;
    @JsonProperty("series")
    @JsonIgnoreProperties(value = "seriesBottleList")
    private final SeriesDTO series;

    public BottleDTO(UUID uuid, String label, Double price, Integer yearOfManufacture, ManufacturerDTO manufacturer, boolean forSale, boolean favorite, boolean unsaleable) {
        this.uuid = uuid;
        this.label = label;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.manufacturer = manufacturer;
        this.forSale = forSale;
        this.favorite = favorite;
        this.unsaleable = unsaleable;
        this.series = null;
    }
}
