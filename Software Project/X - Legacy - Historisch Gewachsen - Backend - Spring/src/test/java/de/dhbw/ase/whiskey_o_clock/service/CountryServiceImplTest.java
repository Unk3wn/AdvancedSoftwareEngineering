package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.helper.DTOMapper;
import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.repository.CountryRepository;
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
import static org.mockito.BDDMockito.doReturn;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryServiceImpl countryService;

    private String countryName = "Testland";
    private String countryAbbreviation = "TES";
    private String newAbbreviation = "NEW";
    private String newCountryName = "New Country Name";
    private UUID countryUUID = UUID.randomUUID();

    @Test
    void saveCountryDTO() {
        CountryDTO countryDTO = new CountryDTO(countryAbbreviation, countryName);
        Country country = DTOMapper.convertDTOToCountry(countryDTO);

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
        List<Country> allCountrys = countryService.getAllCountrys();

        //Validation
        assertEquals("TestA",allCountrys.get(0).getName());
    }

    @Test
    void updateCountry() {

        Country country = new Country(countryUUID, countryAbbreviation, countryName);

        CountryDTO newData = new CountryDTO("NEW", "Neuer Name");

        countryRepository.save(country);

        when(countryRepository.getCountryByUuid(countryUUID)).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(new Country(countryUUID, newAbbreviation, newCountryName));
        when(countryRepository.existsById(countryUUID)).thenReturn(true);

        countryService.updateCountry(countryUUID, newData);
        verify(countryRepository,times(2)).save(any(Country.class));
    }

    @Test
    void deleteCountry() {
        countryService.deleteCountry(countryAbbreviation);
        verify(countryRepository, times(1)).deleteByAbbreviation(countryAbbreviation);
    }

    @Test
    void testDeleteCountry() {
        countryService.deleteCountry(countryUUID);
        verify(countryRepository).deleteById(countryUUID);
    }
}
