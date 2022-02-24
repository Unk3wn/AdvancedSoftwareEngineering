package de.dhbw.ase.plugins.persistence.hibernate.country;

import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.country.CountryMapper;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public class CountryRepositoryBridge implements CountryRepository {

    private SpringDataCountryRepository springDataCountryRepository;

    @Autowired
    public CountryRepositoryBridge(SpringDataCountryRepository springDataCountryRepository){
        this.springDataCountryRepository = springDataCountryRepository;
    }

    @Override
    public Country save(Country country) {
        return springDataCountryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
        return springDataCountryRepository.findAll();
    }

    @Override
    public Country getCountryByAbbreviation(String abbreviation) {
        return springDataCountryRepository.getCountryByAbbreviation(abbreviation);
    }

    @Override
    public Country getCountryByUuid(UUID countryUUID) {
        return springDataCountryRepository.getCountryByUuid(countryUUID);
    }

    @Override
    public boolean existsByAbbreviation(String abbreviation) {
        return springDataCountryRepository.existsByAbbreviation(abbreviation);
    }

    @Override
    public boolean existsById(UUID countryUUID) {
        return existsById(countryUUID);
    }

    @Override
    public void deleteByAbbreviation(String abbreviation) {
        springDataCountryRepository.deleteByAbbreviation(abbreviation);
    }

    @Override
    public void deleteById(UUID countryUUID) {
        springDataCountryRepository.deleteById(countryUUID);
    }
}
