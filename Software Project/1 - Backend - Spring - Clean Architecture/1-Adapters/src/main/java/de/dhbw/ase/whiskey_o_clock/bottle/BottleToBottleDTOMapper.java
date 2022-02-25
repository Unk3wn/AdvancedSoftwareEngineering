package de.dhbw.ase.whiskey_o_clock.bottle;

import de.dhbw.ase.whiskey_o_clock.application.series.SeriesApplicationService;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerToManufacturerDTOMapper;
import de.dhbw.ase.whiskey_o_clock.series.SeriesToSeriesDTOMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BottleToBottleDTOMapper implements Function<Bottle,BottleDTO> {

    private ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper;
    private SeriesToSeriesDTOMapper seriesToSeriesDTOMapper;

    public BottleToBottleDTOMapper(ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper,SeriesToSeriesDTOMapper seriesToSeriesDTOMapper){
        this.manufacturerToManufacturerDTOMapper = manufacturerToManufacturerDTOMapper;
        this.seriesToSeriesDTOMapper = seriesToSeriesDTOMapper;
    }

    @Override
    public BottleDTO apply(Bottle bottle) {
        return map(bottle);
    }

    private BottleDTO map(Bottle bottle) {
        return new BottleDTO(bottle.getUuid(),bottle.getLabel(), bottle.getPrice(), bottle.getYearOfManufacture(),manufacturerToManufacturerDTOMapper.apply(bottle.getManufacturer()), bottle.isForSale(), bottle.isFavorite(), bottle.isUnsaleable(), seriesToSeriesDTOMapper.apply(bottle.getSeries()));
    }

}
