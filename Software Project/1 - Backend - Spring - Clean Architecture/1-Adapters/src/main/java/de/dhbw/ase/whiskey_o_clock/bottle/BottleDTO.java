package de.dhbw.ase.whiskey_o_clock.bottle;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.series.SeriesDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid",
        "label",
        "price",
        "yearOfManufacture",
        "manufacturer",
        "forSale",
        "favorite",
        "unsaleable",
        "series"
})
@Data
@NoArgsConstructor
@Generated("jsonschema2pojo")
public class BottleDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("label")
    private String label;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("yearOfManufacture")
    private Integer yearOfManufacture;
    @JsonProperty("manufacturer")
    private ManufacturerDTO manufacturer;
    @JsonProperty("forSale")
    private Boolean forSale;
    @JsonProperty("favorite")
    private Boolean favorite;
    @JsonProperty("unsaleable")
    private Boolean unsaleable;
    @JsonProperty("series")
    private SeriesDTO series;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public BottleDTO(UUID uuid, String label, Double price, Integer yearOfManufacture, ManufacturerDTO manufacturer, Boolean forSale, Boolean favorite, Boolean unsaleable, SeriesDTO series) {
        this.uuid = uuid;
        this.label = label;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.manufacturer = manufacturer;
        this.forSale = forSale;
        this.favorite = favorite;
        this.unsaleable = unsaleable;
        this.series = series;
    }

    @JsonProperty("uuid")
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("yearOfManufacture")
    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    @JsonProperty("manufacturer")
    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("forSale")
    public Boolean getForSale() {
        return forSale;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("unsaleable")
    public Boolean getUnsaleable() {
        return unsaleable;
    }

    @JsonProperty("series")
    public SeriesDTO getSeries() {
        return series;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
}
