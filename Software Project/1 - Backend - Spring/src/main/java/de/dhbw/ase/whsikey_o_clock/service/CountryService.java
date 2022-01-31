package de.dhbw.ase.whsikey_o_clock.service;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import de.dhbw.ase.whsikey_o_clock.repository.CountryRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country createCountry(String abbreviation, String name) throws NonUniqueObjectException {
        if(null == getCountryByAbbreviation(abbreviation)){
            Country countryToCreate = new Country(abbreviation,name);
            countryRepository.save(countryToCreate);
            return countryToCreate;
        }
        throw new ValidationException("Abbreviation is not unique");
    }

    public void deleteCountry(String abbreviation){
        countryRepository.deleteByAbbreviation(abbreviation);
    }

    public Country getCountryByAbbreviation(String abbreviation){
        return countryRepository.findCountryByAbbreviation(abbreviation);
    }

    public List<Country> getAllCountrys(){
        return countryRepository.findAll();
    }



}
