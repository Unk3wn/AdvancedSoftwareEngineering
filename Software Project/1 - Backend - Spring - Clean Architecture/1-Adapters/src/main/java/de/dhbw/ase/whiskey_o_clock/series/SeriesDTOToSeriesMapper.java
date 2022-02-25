package de.dhbw.ase.whiskey_o_clock.series;

import de.dhbw.ase.whiskey_o_clock.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTOToBottleMapper;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SeriesDTOToSeriesMapper implements Function<SeriesDTO, Series> {

    private BottleDTOToBottleMapper bottleDTOToBottleMapper;

    public SeriesDTOToSeriesMapper(BottleDTOToBottleMapper bottleDTOToBottleMapper){
        this.bottleDTOToBottleMapper = bottleDTOToBottleMapper;
    }

    @Override
    public Series apply(SeriesDTO seriesDTO) {
        return map(seriesDTO);
    }

    private Series map(SeriesDTO seriesDTO) {
        return new Series(seriesDTO.getUuid(),seriesDTO.getLabel(),seriesDTO.getBottleList().stream().map(bottleDTOToBottleMapper).collect(Collectors.toList()));
    }



}
