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
    @JsonIgnoreProperties("bottleList")
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

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("yearOfManufacture")
    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    @JsonProperty("yearOfManufacture")
    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @JsonProperty("manufacturer")
    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("manufacturer")
    public void setManufacturer(ManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("forSale")
    public Boolean getForSale() {
        return forSale;
    }

    @JsonProperty("forSale")
    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    @JsonProperty("favorite")
    public Boolean getFavorite() {
        return favorite;
    }

    @JsonProperty("favorite")
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @JsonProperty("unsaleable")
    public Boolean getUnsaleable() {
        return unsaleable;
    }

    @JsonProperty("unsaleable")
    public void setUnsaleable(Boolean unsaleable) {
        this.unsaleable = unsaleable;
    }

    @JsonProperty("series")
    public SeriesDTO getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(SeriesDTO series) {
        this.series = series;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
