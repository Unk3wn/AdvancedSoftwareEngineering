package de.dhbw.ase.whsikey_o_clock.service;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import de.dhbw.ase.whsikey_o_clock.model.CountryDTO;
import de.dhbw.ase.whsikey_o_clock.repository.CountryRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

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


    @Override
    public void deleteCountry(String abbreviation) {
        countryRepository.deleteByAbbreviation(abbreviation);
    }

    @Override
    public Country getCountryByAbbreviation(String abbreviation) {
        return countryRepository.findCountryByAbbreviation(abbreviation);
    }

    @Override
    public List<Country> getAllCountrys() {
        return countryRepository.findAll();
    }


}
