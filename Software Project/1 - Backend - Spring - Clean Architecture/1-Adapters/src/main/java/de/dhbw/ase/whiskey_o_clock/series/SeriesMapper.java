package de.dhbw.ase.whiskey_o_clock.series;

import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleMapper;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

public class SeriesMapper {

    static ManufacturerRepository manufacturerRepository;
    static BottleRepository bottleRepository;

    @Autowired
    ManufacturerRepository manufacturerRepositoryInitialize;
    @Autowired
    BottleRepository bottleRepositoryInitialize;

    @PostConstruct
    public void init() {
        manufacturerRepository = manufacturerRepositoryInitialize;
        bottleRepository = bottleRepositoryInitialize;
    }

    public SeriesMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    public static SeriesDTO convertSeriesToDTO(Series series) {
        return null;
    }

    public static Series convertDTOToSeries(SeriesDTO seriesDTO) {
        return null;
    }

}
