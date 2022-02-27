package de.dhbw.ase.whiskey_o_clock.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerToManufacturerDTOMapper;
import de.dhbw.ase.whiskey_o_clock.series.SeriesToSeriesDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BottleToBottleDTOMapper implements Function<Bottle, BottleDTO> {

    private ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper;
    private SeriesToSeriesDTOMapper seriesToSeriesDTOMapper;

    @Autowired
    public BottleToBottleDTOMapper(ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper, SeriesToSeriesDTOMapper seriesToSeriesDTOMapper) {
        this.manufacturerToManufacturerDTOMapper = manufacturerToManufacturerDTOMapper;
        this.seriesToSeriesDTOMapper = seriesToSeriesDTOMapper;
    }

    @Override
    public BottleDTO apply(Bottle bottle) {
        return map(bottle);
    }

    private BottleDTO map(Bottle bottle) {
        return new BottleDTO(bottle.getUuid(), bottle
                .getLabel(),
                bottle.getPrice(),
                bottle.getYearOfManufacture(),
                this.manufacturerToManufacturerDTOMapper.apply(bottle.getManufacturer()),
                bottle.isForSale(),
                bottle.isFavorite(),
                bottle.isUnsaleable(),
                this.seriesToSeriesDTOMapper.apply(bottle.getSeries()));
    }

}
