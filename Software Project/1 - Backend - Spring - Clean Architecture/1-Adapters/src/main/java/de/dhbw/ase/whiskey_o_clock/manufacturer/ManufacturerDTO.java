package de.dhbw.ase.whiskey_o_clock.manufacturer;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid",
        "name",
        "originCountry"
})
@Data
@NoArgsConstructor
@Generated("jsonschema2pojo")
public class ManufacturerDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("originCountry")
    private CountryDTO originCountry;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ManufacturerDTO(UUID uuid, String name, CountryDTO originCountry) {
        this.uuid = uuid;
        this.name = name;
        this.originCountry = originCountry;
    }

    @JsonProperty("uuid")
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("originCountry")
    public CountryDTO getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("originCountry")
    public void setOriginCountry(CountryDTO originCountry) {
        this.originCountry = originCountry;
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
