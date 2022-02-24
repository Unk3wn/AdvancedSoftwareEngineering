package de.dhbw.ase.whiskey_o_clock.manufacturer;

import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class ManufacturerMapper {

    static CountryRepository countryRepository;

    @Autowired
    CountryRepository countryRepositoryInitialize;

    @PostConstruct
    public void init() {
        countryRepository = countryRepositoryInitialize;
    }

    public ManufacturerMapper() throws InstantiationException {
        throw new InstantiationException("Utility Class should not be instanciated!");
    }

    public static ManufacturerDTO convertManufacturerToDTO(Manufacturer manufacturer) {
        return null;
    }

    public static Manufacturer convertDTOToManufacturer(ManufacturerDTO manufacturerDTO) {
        return null;
    }

}
