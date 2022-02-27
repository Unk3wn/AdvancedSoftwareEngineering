package de.dhbw.ase.whiskey_o_clock.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.builder.BottleBuilder;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTOToManufacturer;
import de.dhbw.ase.whiskey_o_clock.series.SeriesDTOToSeriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BottleDTOToBottleMapper implements Function<BottleDTO, Bottle> {

    private ManufacturerDTOToManufacturer manufacturerDTOToManufacturer;
    private SeriesDTOToSeriesMapper seriesDTOToSeriesMapper;

    @Autowired
    public BottleDTOToBottleMapper(ManufacturerDTOToManufacturer manufacturerDTOToManufacturer, SeriesDTOToSeriesMapper seriesDTOToSeriesMapper) {
        this.manufacturerDTOToManufacturer = manufacturerDTOToManufacturer;
        this.seriesDTOToSeriesMapper = seriesDTOToSeriesMapper;
    }

    @Override
    public Bottle apply(BottleDTO bottleDTO) {
        return map(bottleDTO);
    }

    private Bottle map(BottleDTO bottleDTO) {
        BottleBuilder newBottle = new BottleBuilder(bottleDTO.getLabel());
        newBottle.uuid(bottleDTO.getUuid())
                .price(bottleDTO.getPrice().doubleValue())
                .yearOfManufacture(bottleDTO.getYearOfManufacture().intValue())
                .manufacturer(this.manufacturerDTOToManufacturer.apply(bottleDTO.getManufacturer()));
        if (bottleDTO.getForSale() != null) {
            newBottle.forSale(bottleDTO.getForSale().booleanValue());
        } else {
            newBottle.forSale(false);
        }
        if (bottleDTO.getFavorite() != null) {
            newBottle.favorite(bottleDTO.getFavorite().booleanValue());
        } else {
            newBottle.favorite(false);
        }
        if (bottleDTO.getUnsaleable() != null) {
            newBottle.unsaleable(bottleDTO.getUnsaleable().booleanValue());
        } else {
            newBottle.unsaleable(false);
        }
        if (bottleDTO.getSeries() != null) {
            newBottle.series(this.seriesDTOToSeriesMapper.apply(bottleDTO.getSeries()));
        } else {
            newBottle.series(null);
        }
        return newBottle.build();
    }
}
