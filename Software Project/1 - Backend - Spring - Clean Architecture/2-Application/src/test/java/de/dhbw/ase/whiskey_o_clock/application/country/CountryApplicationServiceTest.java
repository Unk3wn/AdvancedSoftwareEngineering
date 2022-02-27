package de.dhbw.ase.whiskey_o_clock.application.country;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CountryApplicationServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryApplicationService countryApplicationService;

    private static final String COUNTRY_NAME = "Testland";
    private static final String COUNTRY_ABBREVIATION = "TES";
    private static final String NEW_ABBREVIATION = "NEW";
    private static final String NEW_COUNTRY_NAME = "New Country Name";
    private static final UUID COUNTRY_UUID = UUID.randomUUID();

    @Test
    void saveCountry() {
        Country country = new Country(COUNTRY_ABBREVIATION, COUNTRY_NAME);

        when(countryRepository.save(country)).thenReturn(country);
        Country createdCountry = countryApplicationService.saveCountry(country);

        verify(countryRepository).save(any(Country.class));
        assertEquals(country, createdCountry);
    }

    @Test
    void saveCountryVariables() {
        Country country = new Country(COUNTRY_ABBREVIATION, COUNTRY_NAME);

        when(countryRepository.save(any(Country.class))).thenReturn(country);
        Country createdCountry = countryApplicationService.saveCountry(COUNTRY_ABBREVIATION, COUNTRY_NAME);

        verify(countryRepository).save(any(Country.class));
        assertThat(createdCountry).isEqualTo(country);
    }

    @Test
    void getAllCountrys() {
        // Preperation
        when(countryRepository.findAll()).thenReturn(Arrays.asList(
                new Country("TEA", "TestA"),
                new Country("TEB", "TestB"),
                new Country("TEC", "TestC")
        ));

        // Actions
        List<Country> allCountrys = countryApplicationService.getAllCountrys();

        //Validation
        assertEquals("TestA", allCountrys.get(0).getName());
    }

    @Test
    void updateCountry() {

        Country country = new Country(COUNTRY_UUID, COUNTRY_ABBREVIATION, COUNTRY_NAME);

        countryRepository.save(country);

        when(countryRepository.getCountryByUuid(COUNTRY_UUID)).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(country);
        when(countryRepository.existsById(COUNTRY_UUID)).thenReturn(true);

        country.setAbbreviation(NEW_ABBREVIATION);
        country.setName(NEW_ABBREVIATION);

        when(countryRepository.save(country)).thenReturn(new Country(COUNTRY_UUID, NEW_ABBREVIATION, NEW_COUNTRY_NAME));
        country = countryApplicationService.updateCountry(country);

        assertEquals(NEW_COUNTRY_NAME, country.getName());
        assertEquals(NEW_ABBREVIATION, country.getAbbreviation());
        verify(countryRepository, times(2)).save(any(Country.class));
    }

    @Test
    void deleteCountry() {
        countryApplicationService.deleteCountry(COUNTRY_ABBREVIATION);
        verify(countryRepository, times(1)).deleteByAbbreviation(COUNTRY_ABBREVIATION);
    }

    @Test
    void testDeleteCountry() {
        countryApplicationService.deleteCountry(COUNTRY_UUID);
        verify(countryRepository).deleteById(COUNTRY_UUID);
    }
}
