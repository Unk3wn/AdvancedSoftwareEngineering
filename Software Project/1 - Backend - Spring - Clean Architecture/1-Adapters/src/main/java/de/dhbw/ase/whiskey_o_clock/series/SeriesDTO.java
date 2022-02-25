package de.dhbw.ase.whiskey_o_clock.series;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SeriesDTO implements Serializable {
    @JsonProperty("uuid")
    private final UUID uuid;
    @JsonProperty("seriesLabel")
    private final String seriesLabel;
    @JsonProperty("bottleList")
    private final List<BottleDTO> seriesBottleList;

    public SeriesDTO(String seriesLabel) {
        this.uuid = null;
        this.seriesLabel = seriesLabel;
        this.seriesBottleList = new LinkedList<>();
    }
}
