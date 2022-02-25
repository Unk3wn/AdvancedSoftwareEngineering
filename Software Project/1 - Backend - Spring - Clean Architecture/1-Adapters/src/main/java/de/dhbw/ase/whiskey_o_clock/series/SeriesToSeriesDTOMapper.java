package de.dhbw.ase.whiskey_o_clock.series;

import de.dhbw.ase.whiskey_o_clock.bottle.BottleToBottleDTOMapper;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SeriesToSeriesDTOMapper implements Function<Series, SeriesDTO> {

    private BottleToBottleDTOMapper bottleToBottleDTOMapper;

    public SeriesToSeriesDTOMapper(BottleToBottleDTOMapper bottleToBottleDTOMapper){
        this.bottleToBottleDTOMapper = bottleToBottleDTOMapper;
    }

    @Override
    public SeriesDTO apply(Series series) {
        return map(series);
    }

    private SeriesDTO map(Series series) {
        return new SeriesDTO(series.getUuid(),series.getLabel(),series.getBottleList().stream().map(bottleToBottleDTOMapper).collect(Collectors.toList()));
    }

}
