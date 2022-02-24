package de.dhbw.ase.whiskey_o_clock.application.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class CountryApplicationService{

    private CountryRepository countryRepository;

    @Autowired
    public CountryApplicationService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
    public Country saveCountry(String abbreviation, String name) throws NonUniqueObjectException {
        if (!countryRepository.existsByAbbreviation(abbreviation)) {
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
    public List<Country> getAllCountrys() {
        return countryRepository.findAll();
    }

    public Country getCountryByAbbreviation(String abbreviation) {
        return countryRepository.getCountryByAbbreviation(abbreviation);
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
    public Country updateCountry(UUID countryUUID, Country convertedDTO) {
        return null;
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
    public void deleteCountry(String abbreviation) {
        countryRepository.deleteByAbbreviation(abbreviation);
    }

    public void deleteCountry(UUID uuid) {
        countryRepository.deleteById(uuid);
    }

    /************************************************************************************************************************************/
}
