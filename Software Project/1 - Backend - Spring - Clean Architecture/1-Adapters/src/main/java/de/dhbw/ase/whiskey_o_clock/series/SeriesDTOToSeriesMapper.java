package de.dhbw.ase.whiskey_o_clock.series;

import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SeriesDTOToSeriesMapper implements Function<SeriesDTO, Series> {

    @Override
    public Series apply(SeriesDTO seriesDTO) {
        return map(seriesDTO);
    }

    private Series map(SeriesDTO seriesDTO) {
        return new Series(seriesDTO.getUuid(), seriesDTO.getLabel());
    }


}
