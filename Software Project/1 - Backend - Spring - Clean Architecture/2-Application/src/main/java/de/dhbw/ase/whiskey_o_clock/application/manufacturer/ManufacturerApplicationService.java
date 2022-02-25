package de.dhbw.ase.whiskey_o_clock.application.manufacturer;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class ManufacturerApplicationService {

    private CountryRepository countryRepository;
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerApplicationService(CountryRepository countryRepository, ManufacturerRepository manufacturerRepository) {
        this.countryRepository = countryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer createManufacturer(String name, String countryAbbreviation) throws NonUniqueObjectException {
        if (!countryRepository.existsByAbbreviation(countryAbbreviation)) {
            throw new ValidationException("Country-Abbreviation is not valid!");
        }
        Country targetCountry = countryRepository.getCountryByAbbreviation(countryAbbreviation);
        if (!manufacturerRepository.existsByName(name) || !targetCountry.equals(manufacturerRepository.getManufacturerByName(name))) {
            Manufacturer newManufacturer = new Manufacturer(name, countryRepository.getCountryByAbbreviation(countryAbbreviation));
            manufacturerRepository.save(newManufacturer);
            return newManufacturer;
        }
        throw new ValidationException(String.format("Manufacturer '%s' with the origin Country '%s' is already in the Database!", name, targetCountry.getName()));
    }

    /**
     * @return
     **********************************************************************************************************************************/
    /*
         _____                _
        |  __ \              | |
        | |__) |___  __ _  __| |
        |  _  // _ \/ _` |/ _` |
        | | \ \  __/ (_| | (_| |
        |_|  \_\___|\__,_|\__,_|
    */
    public Manufacturer getManufacturerByUUID(UUID uuid) {
        return manufacturerRepository.getManufacturerByUuid(uuid);
    }

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
    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        if (manufacturerRepository.existsById(manufacturer.getUuid())) {
            Manufacturer targetCountry = manufacturerRepository.getManufacturerByUuid(manufacturer.getUuid());
            targetCountry.setName(manufacturer.getName());
            targetCountry.setOriginCountry(manufacturer.getOriginCountry());
            return manufacturerRepository.save(targetCountry);
        }
        throw new ValidationException("Country not found!");
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
    public void deleteManufacturerByUUID(UUID uuid) {
        manufacturerRepository.deleteById(uuid);
    }

    /************************************************************************************************************************************/
}
