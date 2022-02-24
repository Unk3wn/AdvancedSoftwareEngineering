package de.dhbw.ase.whiskey_o_clock.bottle;

import de.dhbw.ase.whiskey_o_clock.application.manufacturer.ManufacturerApplicationService;
import de.dhbw.ase.whiskey_o_clock.application.series.SeriesApplicationService;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.builder.BottleBuilder;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.validation.ValidationException;

public class BottleMapper {

    static ManufacturerApplicationService manufacturerApplicationService;
    static SeriesApplicationService seriesApplicationService;

    @Autowired
    ManufacturerApplicationService manufacturerApplicationServiceInitialize;
    @Autowired
    SeriesApplicationService seriesApplicationServiceInitialize;

    @PostConstruct
    public void init() {
        manufacturerApplicationService = manufacturerApplicationServiceInitialize;
        seriesApplicationService = seriesApplicationServiceInitialize;
    }

    public BottleMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    public static BottleDTO convertBottleToDTO(Bottle bottle) {
        return null;
    }

    public static Bottle convertDTOToBottle(BottleDTO bottleDTO) {
       return null;
    }
}
