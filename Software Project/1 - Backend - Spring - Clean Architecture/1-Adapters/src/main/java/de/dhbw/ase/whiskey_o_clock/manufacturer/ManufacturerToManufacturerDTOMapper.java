package de.dhbw.ase.whiskey_o_clock.manufacturer;

import de.dhbw.ase.whiskey_o_clock.country.CountryToCountryDTOMapper;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ManufacturerToManufacturerDTOMapper implements Function<Manufacturer, ManufacturerDTO> {

    private CountryToCountryDTOMapper countryToCountryDTOMapper;

    public ManufacturerToManufacturerDTOMapper(CountryToCountryDTOMapper countryToCountryDTOMapper) {
        this.countryToCountryDTOMapper = countryToCountryDTOMapper;
    }

    @Override
    public ManufacturerDTO apply(Manufacturer manufacturer) {
        return map(manufacturer);
    }

    private ManufacturerDTO map(Manufacturer manufacturer) {
        return new ManufacturerDTO(manufacturer.getUuid(), countryToCountryDTOMapper.apply(manufacturer.getOriginCountry()), manufacturer.getName());
    }

}
