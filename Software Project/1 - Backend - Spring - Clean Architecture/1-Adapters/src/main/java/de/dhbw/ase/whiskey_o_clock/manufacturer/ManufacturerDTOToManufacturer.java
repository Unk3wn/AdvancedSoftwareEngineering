package de.dhbw.ase.whiskey_o_clock.manufacturer;

import de.dhbw.ase.whiskey_o_clock.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTOToCountryMapper;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ManufacturerDTOToManufacturer implements Function<ManufacturerDTO, Manufacturer> {

    private CountryDTOToCountryMapper countryDTOToCountryMapper;

    @Autowired
    public ManufacturerDTOToManufacturer(CountryDTOToCountryMapper countryDTOToCountryMapper){
        this.countryDTOToCountryMapper = countryDTOToCountryMapper;
    }

    @Override
    public Manufacturer apply(ManufacturerDTO manufacturerDTO) {
        return map(manufacturerDTO);
    }

    private Manufacturer map(ManufacturerDTO manufacturerDTO) {
        return new Manufacturer(manufacturerDTO.getName(),countryDTOToCountryMapper.apply(manufacturerDTO.getOriginCountry()));
    }

}
