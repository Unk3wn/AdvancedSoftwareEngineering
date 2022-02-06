package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.repository.CountryRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

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
    public Country saveCountry(CountryDTO countryDTO) {
        return saveCountry(countryDTO.getAbbreviation(), countryDTO.getName());
    }

    @Override
    public Country saveCountry(String abbreviation, String name) throws NonUniqueObjectException {
        if (null == getCountryByAbbreviation(abbreviation)) {
            Country countryToCreate = new Country(abbreviation, name);
            countryRepository.save(countryToCreate);
            return countryToCreate;
        }
        throw new ValidationException("Abbreviation is not unique");
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
    public Country getCountryByAbbreviation(String abbreviation) {
        return countryRepository.findCountryByAbbreviation(abbreviation);
    }

    @Override
    public List<Country> getAllCountrys() {
        return countryRepository.findAll();
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
    public Country updateCountry(UUID countryUUID, CountryDTO countryDTO) {
        if (null != countryRepository.findCountryByUuid(countryUUID)) {
            Country foundCountry = countryRepository.findCountryByUuid(countryUUID);
            foundCountry.updateFromDTO(countryDTO);
            countryRepository.save(foundCountry);
            return foundCountry;
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
    public void deleteCountry(String abbreviation) {
        countryRepository.deleteByAbbreviation(abbreviation);
    }

    /************************************************************************************************************************************/
}
