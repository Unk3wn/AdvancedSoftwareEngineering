package de.dhbw.ase.whiskey_o_clock.country;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
        "uuid",
        "abbreviation",
        "name"
})
@Data
@NoArgsConstructor
@Generated("jsonschema2pojo")
public class CountryDTO {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("abbreviation")
    private String abbreviation;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uuid")
    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    @JsonProperty("abbreviation")
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public CountryDTO(UUID uuid, String abbreviation, String name) {
        this.uuid = uuid.toString();
        this.abbreviation = abbreviation;
        this.name = name;
    }
}
