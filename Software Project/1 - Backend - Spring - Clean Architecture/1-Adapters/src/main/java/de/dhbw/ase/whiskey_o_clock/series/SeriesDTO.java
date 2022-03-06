package de.dhbw.ase.whiskey_o_clock.series;

import com.fasterxml.jackson.annotation.*;
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
        "bottleList"
})
@Data
@NoArgsConstructor
@Generated("jsonschema2pojo")
public class SeriesDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("label")
    private String label;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SeriesDTO(UUID uuid, String label) {
        this.uuid = uuid;
        this.label = label;
    }

    @JsonProperty("uuid")
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
}
