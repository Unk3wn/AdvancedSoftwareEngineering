package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Autowired
    CountryService countryService;


    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */

    @Override
    public Manufacturer createManufacturer(ManufacturerDTO manufacturerDTO) {
        return createManufacturer(manufacturerDTO.getManufacturerName(), manufacturerDTO.getOriginCountryAbbreviation());
    }
    @Override
    public Manufacturer createManufacturer(String name, String countryAbbreviation) throws NonUniqueObjectException {
        if (null == (countryService.getCountryByAbbreviation(countryAbbreviation))) {
            throw new ValidationException("Country-Abbreviation is not valid!");
        }
        Country targetCountry = countryService.getCountryByAbbreviation(countryAbbreviation);
        if (null == (getManufacturerByName(name)) || !targetCountry.equals(getManufacturerByName(name).getOriginCountry())) {
            Manufacturer newManufacturer = new Manufacturer(name, countryService.getCountryByAbbreviation(countryAbbreviation));
            manufacturerRepository.save(newManufacturer);
            return newManufacturer;
        }
        throw new ValidationException(String.format("Manufacturer '%s' with the origin Country '%s' is already in the Database!", name, targetCountry.getName()));
    }

    /************************************************************************************************************************************/
    /*
         _____                _
        |  __ \              | |
        | |__) |___  __ _  __| |
        |  _  // _ \/ _` |/ _` |
        | | \ \  __/ (_| | (_| |
        |_|  \_\___|\__,_|\__,_|
    */

    @Override
    public Manufacturer getManufacturerByName(String name) {
        return manufacturerRepository.getManufacturerByName(name);
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    /************************************************************************************************************************************/
    /*
         _    _           _       _
        | |  | |         | |     | |
        | |  | |_ __   __| | __ _| |_ ___
        | |  | | '_ \ / _` |/ _` | __/ _ \
        | |__| | |_) | (_| | (_| | ||  __/
        \____/| .__/ \__,_|\__,_|\__\___|
              | |
              |_|
    */

    @Override
    public Manufacturer updateManufacturer(UUID manufacturerUUID, ManufacturerDTO manufacturerDTO) {
        if (null != manufacturerRepository.getManufacturerByUuid(manufacturerUUID)) {
            Manufacturer foundManufacturer = manufacturerRepository.getManufacturerByUuid(manufacturerUUID);
            foundManufacturer.updateFromDTO(manufacturerDTO,countryService.getCountryByAbbreviation(manufacturerDTO.getOriginCountryAbbreviation()));
            manufacturerRepository.save(foundManufacturer);
            return foundManufacturer;
        }
        throw new ValidationException("UUID is not known");
    }

    /************************************************************************************************************************************/
    /*
         _____       _      _
        |  __ \     | |    | |
        | |  | | ___| | ___| |_ ___
        | |  | |/ _ \ |/ _ \ __/ _ \
        | |__| |  __/ |  __/ ||  __/
        |_____/ \___|_|\___|\__\___|
    */

    @Override
    public void deleteManufacturerByName(String name) {
        manufacturerRepository.deleteByName(name);
    }

    /************************************************************************************************************************************/
}
