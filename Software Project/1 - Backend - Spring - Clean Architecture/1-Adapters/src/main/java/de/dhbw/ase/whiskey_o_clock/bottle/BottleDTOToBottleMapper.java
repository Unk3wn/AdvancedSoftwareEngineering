package de.dhbw.ase.whiskey_o_clock.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.builder.BottleBuilder;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BottleDTOToBottleMapper implements Function<BottleDTO, Bottle> {

    private ManufacturerRepository manufacturerRepository;
    private SeriesRepository seriesRepository;

    public BottleDTOToBottleMapper(ManufacturerRepository manufacturerRepository, SeriesRepository seriesRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Bottle apply(BottleDTO bottleDTO) {
        return map(bottleDTO);
    }

    private Bottle map(BottleDTO bottleDTO) {
        BottleBuilder newBottle = new BottleBuilder(bottleDTO.getLabel());
        newBottle.uuid(bottleDTO.getUuid())
                .price(bottleDTO.getPrice())
                .yearOfManufacture(bottleDTO.getYearOfManufacture())
                .manufacturer(manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer().getUuid()))
                .forSale(bottleDTO.getForSale())
                .favorite(bottleDTO.getFavorite())
                .unsaleable(bottleDTO.getUnsaleable());

        if (bottleDTO.getSeries() != null) {
            newBottle.series(seriesRepository.getSeriesByUuid(bottleDTO.getSeries().getUuid()));
        } else {
            newBottle.series(null);
        }

        return newBottle.build();
    }

}
