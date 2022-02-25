package de.dhbw.ase.whiskey_o_clock.helper;

import de.dhbw.ase.whiskey_o_clock.model.*;
import de.dhbw.ase.whiskey_o_clock.model.builder.BottleBuilder;
import de.dhbw.ase.whiskey_o_clock.repository.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.repository.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.repository.SeriesRepository;

import javax.validation.ValidationException;
import java.util.LinkedList;
import java.util.List;

public class DTOMapper {

    private DTOMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    // Hier sollte man auf ein Framework zurückgeifen, wenn mehr Entitys verwendet werden sollen!

    public static CountryDTO convertCountryToDTO(Country country) {
        return new CountryDTO(country.getAbbreviation(), country.getName());
    }

    public static Country convertDTOToCountry(CountryDTO countryDTO) {
        return new Country(countryDTO.getCountryAbbreviation(), countryDTO.getCountryName());
    }

    public static void updateCountryWithDTO(Country country, CountryDTO countryDTO) {
        if (countryDTO.getCountryName() != null) {
            country.setName(countryDTO.getCountryName());
        }
        if (countryDTO.getCountryAbbreviation() != null) {
            country.setAbbreviation(countryDTO.getCountryAbbreviation());
        }
    }

    public static void updateManufacturerWithDTO(CountryRepository countryRepository, Manufacturer manufacturer, ManufacturerDTO manufacturerDTO) {
        if (manufacturerDTO.getManufacturerName() != null) {
            manufacturer.setName(manufacturerDTO.getManufacturerName());
        }
        if (manufacturerDTO.getOriginCountryAbbreviation() != null) {
            manufacturer.setOriginCountry(countryRepository.getCountryByAbbreviation(manufacturerDTO.getOriginCountryAbbreviation()));
        }
    }

    public static BottleDTO convertBottleToDTO(Bottle bottle) {
        if (bottle.getSeries() != null) {
            return new BottleDTO(bottle.getLabel(), bottle.getPrice(), bottle.getYearOfManufacture(), bottle.getManufacturer().getUuid(), bottle.isForSale(), bottle.isFavorite(), bottle.isUnsaleable(), bottle.getSeries().getUuid());
        } else {
            return new BottleDTO(bottle.getLabel(), bottle.getPrice(), bottle.getYearOfManufacture(), bottle.getManufacturer().getUuid(), bottle.isForSale(), bottle.isFavorite(), bottle.isUnsaleable(), null);
        }
    }

    public static Bottle convertDTOToBottle(ManufacturerRepository manufacturerRepository, SeriesRepository seriesRepository, BottleDTO bottleDTO) {
        if (null != bottleDTO.getLabel()) {
            BottleBuilder bottleBuilder = new BottleBuilder(bottleDTO.getLabel());
            if (null != bottleDTO.getPrice())
                bottleBuilder.price(bottleDTO.getPrice());
            if (null != bottleDTO.getYearOfManufacture())
                bottleBuilder.yearOfManufacture(bottleDTO.getYearOfManufacture());
            if (null != bottleDTO.getManufacturer())
                bottleBuilder.manufacturer(manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()));
            if (bottleDTO.isForSale())
                bottleBuilder.forSale(bottleDTO.isForSale());
            if (bottleDTO.isFavorite())
                bottleBuilder.favorite(bottleDTO.isFavorite());
            if (bottleDTO.isUnsaleable())
                bottleBuilder.unsaleable(bottleDTO.isUnsaleable());
            if (null != bottleDTO.getSeries())
                bottleBuilder.series(seriesRepository.getSeriesByUuid(bottleDTO.getSeries()));
            return bottleBuilder.build();
        }
        throw new ValidationException("Label not given!");
    }

    public static void updateBottleWithDTO(ManufacturerRepository manufacturerRepository, SeriesRepository seriesRepository, BottleRepository bottleRepository, Bottle bottle, BottleDTO bottleDTO) {
        if (null != bottle.getLabel() && !bottle.getLabel().equals(bottleDTO.getLabel())) {
            bottle.setLabel(bottleDTO.getLabel());
        }
        if (null != bottleDTO.getPrice() && bottle.getPrice() == bottleDTO.getPrice()) {
            bottle.setPrice(bottleDTO.getPrice());
        }
        if (null != bottleDTO.getYearOfManufacture() && bottle.getYearOfManufacture() == bottleDTO.getYearOfManufacture()) {
            bottle.setYearOfManufacture(bottleDTO.getYearOfManufacture());
        }
        if (!bottle.getManufacturer().equals(manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()))) {
            bottle.setManufacturer(manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()));
        } else if (null == bottleDTO.getManufacturer()) {
            bottle.setManufacturer(null);
        }
        if (bottle.isForSale() != bottleDTO.isForSale()) {
            bottle.setForSale(bottleDTO.isForSale());
        }
        if (bottle.isFavorite() != bottleDTO.isFavorite()) {
            bottle.setFavorite(bottleDTO.isFavorite());
        }
        if (bottle.isUnsaleable() != bottleDTO.isUnsaleable()) {
            bottle.setUnsaleable(bottleDTO.isUnsaleable());
        }
        if (null == bottleDTO.getSeries()) {
            bottle.setSeries(null);
        } else if (null != bottleDTO.getSeries() || null != bottle.getSeries() && bottle.getSeries() != seriesRepository.getSeriesByUuid(bottleDTO.getSeries())) {
            bottle.setSeries(seriesRepository.getSeriesByUuid(bottleDTO.getSeries()));
        }
        bottleRepository.save(bottle);
    }

    public static SeriesDTO convertSeriesToDTO(Series series) {
        List<BottleDTO> bottleDTOList = new LinkedList<>();
        for (Bottle bottle : series.getBottleList()) {
            bottleDTOList.add(convertBottleToDTO(bottle));
        }
        return new SeriesDTO(series.getLabel(), bottleDTOList);
    }

    public static Series convertDTOToSeries(BottleRepository bottleRepository, ManufacturerRepository manufacturerRepository, SeriesDTO seriesDTO) {
        if (null != seriesDTO.getSeriesBottleList()) {
            List<Bottle> bottleList = new LinkedList<>();
            for (BottleDTO bottleDTO : seriesDTO.getSeriesBottleList()) {
                // Hierbei wird ausgegangen, dass beim Selben Manufacturer keine zwei Whiskey's existieren, die den selben Namen haben
                bottleList.add(bottleRepository.getFirstBottleByLabelAndManufacturer(bottleDTO.getLabel(), manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer())));
            }
            return new Series(seriesDTO.getSeriesLabel(), bottleList);
        } else {
            return new Series(seriesDTO.getSeriesLabel());
        }

    }

}
