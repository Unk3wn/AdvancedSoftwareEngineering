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
    CountryRepository countryRepository;

    @InjectMocks
    CountryApplicationService countryApplicationService;

    private String countryName = "Testland";
    private String countryAbbreviation = "TES";
    private String newAbbreviation = "NEW";
    private String newCountryName = "New Country Name";
    private UUID countryUUID = UUID.randomUUID();

    @Test
    void saveCountry() {
        Country country = new Country(countryAbbreviation, countryName);

        when(countryRepository.save(country)).thenReturn(country);
        Country createdCountry = countryApplicationService.saveCountry(country);

        verify(countryRepository).save(any(Country.class));
        assertEquals(country, createdCountry);
    }

    @Test
    void saveCountryVariables() {
        Country country = new Country(countryAbbreviation, countryName);

        when(countryRepository.save(any(Country.class))).thenReturn(country);
        Country createdCountry = countryApplicationService.saveCountry(countryAbbreviation, countryName);

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

        Country country = new Country(countryUUID, countryAbbreviation, countryName);

        countryRepository.save(country);

        when(countryRepository.getCountryByUuid(countryUUID)).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(country);
        when(countryRepository.existsById(countryUUID)).thenReturn(true);

        country.setAbbreviation(newAbbreviation);
        country.setName(newAbbreviation);

        when(countryRepository.save(country)).thenReturn(new Country(countryUUID, newAbbreviation, newCountryName));
        country = countryApplicationService.updateCountry(country);

        assertEquals(newCountryName, country.getName());
        assertEquals(newAbbreviation, country.getAbbreviation());
        verify(countryRepository, times(2)).save(any(Country.class));
    }

    @Test
    void deleteCountry() {
        countryApplicationService.deleteCountry(countryAbbreviation);
        verify(countryRepository, times(1)).deleteByAbbreviation(countryAbbreviation);
    }

    @Test
    void testDeleteCountry() {
        countryApplicationService.deleteCountry(countryUUID);
        verify(countryRepository).deleteById(countryUUID);
    }
}
