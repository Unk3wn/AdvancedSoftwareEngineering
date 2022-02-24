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
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

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
    void saveCountryDTO() {
        Country country = new Country(countryAbbreviation, countryName);

        when(countryRepository.save(country)).thenReturn(country);

        Country createdCountry = countryRepository.save(country);

        verify(countryRepository).save(any(Country.class));
        assertThat(createdCountry).isEqualTo(country);
    }

    @Test
    void saveCountry() {
        Country country = new Country(countryAbbreviation, countryName);

        when(countryRepository.save(country)).thenReturn(country);
        Country createdCountry = countryRepository.save(country);

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
        assertEquals("TestA",allCountrys.get(0).getName());
    }

    @Test
    void updateCountry() {

        Country country = new Country(countryUUID, countryAbbreviation, countryName);

        countryRepository.save(country);

        when(countryRepository.getCountryByUuid(countryUUID)).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(new Country(countryUUID, newAbbreviation, newCountryName));
        when(countryRepository.existsById(countryUUID)).thenReturn(true);

        countryApplicationService.updateCountry(countryUUID, new Country(newAbbreviation,newCountryName));
        verify(countryRepository,times(2)).save(any(Country.class));
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