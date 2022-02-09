package de.dhbw.ase.whiskey_o_clock.helper;

import de.dhbw.ase.whiskey_o_clock.model.*;
import de.dhbw.ase.whiskey_o_clock.repository.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.repository.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

public class DTOMapper {

    private DTOMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    static CountryRepository countryRepository;
    static ManufacturerRepository manufacturerRepository;
    static BottleRepository bottleRepository;
    static SeriesRepository seriesRepository;

    @Autowired
    CountryRepository countryRepositoryImpl;
    @Autowired
    ManufacturerRepository manufacturerRepositoryImpl;
    @Autowired
    BottleRepository bottleRepositoryImpl;
    @Autowired
    SeriesRepository seriesRepositoryImpl;

    @PostConstruct
    public void init(){
        countryRepository = countryRepositoryImpl;
        manufacturerRepository = manufacturerRepositoryImpl;
        bottleRepository = bottleRepositoryImpl;
        seriesRepository = seriesRepositoryImpl;
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

    public static ManufacturerDTO convertManufacturerToDTO(Manufacturer manufacturer) {
        return new ManufacturerDTO(manufacturer.getName(), manufacturer.getOriginCountry().getAbbreviation());
    }

    public static Manufacturer convertDTOToManufacturer(ManufacturerDTO manufacturerDTO) {
        return new Manufacturer(manufacturerDTO.getManufacturerName(), countryRepository.getCountryByAbbreviation(manufacturerDTO.getOriginCountryAbbreviation()));
    }

    public static void updateManufacturerWithDTO(Manufacturer manufacturer, ManufacturerDTO manufacturerDTO) {
        if (manufacturerDTO.getManufacturerName() != null) {
            manufacturer.setName(manufacturerDTO.getManufacturerName());
        }
        if (manufacturerDTO.getOriginCountryAbbreviation() != null) {
            manufacturer.setOriginCountry(countryRepository.getCountryByAbbreviation(manufacturerDTO.getOriginCountryAbbreviation()));
        }
    }

    public static BottleDTO convertBottleToDTO(Bottle bottle) {
        return new BottleDTO(bottle.getLabel(), bottle.getPrice(), bottle.getYearOfManufacture(), bottle.getManufacturer().getUuid());
    }

    public static Bottle convertDTOToBottle(BottleDTO bottleDTO) {
        return new Bottle(bottleDTO.getLabel(), bottleDTO.getPrice(), bottleDTO.getYearOfManufacture(), manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()));
    }

    public static void updateBottleWithDTO(Bottle bottle, BottleDTO bottleDTO) {
        if (bottleDTO.getLabel() != null) {
            bottle.setLabel(bottleDTO.getLabel());
        }
        if (bottleDTO.getPrice() != null) {
            bottle.setPrice(bottleDTO.getPrice());
        }
        if (bottleDTO.getYearOfManufacture() != null) {
            bottle.setYearOfManufacture(bottleDTO.getYearOfManufacture());
        }
        if (bottleDTO.getManufacturer() != null) {
            bottle.setManufacturer(manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()));
        }
    }

    public static SeriesDTO convertSeriesToDTO(Series series) {
        List<BottleDTO> bottleDTOList = new LinkedList<>();
        for (Bottle bottle : series.getBottleList()) {
            bottleDTOList.add(convertBottleToDTO(bottle));
        }
        return new SeriesDTO(series.getLabel(), bottleDTOList);
    }

    public static Series convertDTOToSeries(SeriesDTO seriesDTO) {
        List<Bottle> bottleList = new LinkedList<>();
        for (BottleDTO bottleDTO : seriesDTO.getSeriesBottleList()) {
            // Hierbei wird ausgegangen, dass beim Selben Manufacturer keine zwei Whiskey's existieren, die den selben Namen haben
            bottleList.add(bottleRepository.getFirstBottleByLabelAndManufacturer(bottleDTO.getLabel(), manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer())));
        }
        return new Series(seriesDTO.getSeriesLabel(), bottleList);
    }

    public static void updateSeriesWithDTO(Series series, SeriesDTO seriesDTO) {
        if (seriesDTO.getSeriesLabel() != null) {
            series.setLabel(seriesDTO.getSeriesLabel());
        }
        if (seriesDTO.getSeriesBottleList() != null) {
            series.clearBottleList();
            for (BottleDTO bottleDTO : seriesDTO.getSeriesBottleList()) {
                series.addBottle(bottleRepository.getFirstBottleByLabelAndManufacturer(bottleDTO.getLabel(), manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer())));
            }
        }
    }

}
