package de.dhbw.ase.whiskey_o_clock.series;

import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SeriesToSeriesDTOMapper implements Function<Series, SeriesDTO> {

    @Override
    public SeriesDTO apply(Series series) {
        return map(series);
    }

    private SeriesDTO map(Series series) {
        return new SeriesDTO(series.getUuid(),series.getLabel(),null);
    }

}
