package de.dhbw.ase.whiskey_o_clock.country;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public CountryDTO(UUID uuid, String abbreviation, String name) {
        this.uuid = uuid.toString();
        this.abbreviation = abbreviation;
        this.name = name;
    }

    @JsonProperty("uuid")
    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    @JsonProperty("abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
}
