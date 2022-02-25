package de.dhbw.ase.whiskey_o_clock;

import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonProperty("bottleList")
    private List<BottleDTO> bottleList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("bottleList")
    public List<BottleDTO> getBottleList() {
        return bottleList;
    }

    @JsonProperty("bottleList")
    public void setBottleList(List<BottleDTO> bottleList) {
        this.bottleList = bottleList;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public SeriesDTO(UUID uuid, String label) {
        this.uuid = uuid;
        this.label = label;
        this.bottleList = new LinkedList<>();
    }
}
