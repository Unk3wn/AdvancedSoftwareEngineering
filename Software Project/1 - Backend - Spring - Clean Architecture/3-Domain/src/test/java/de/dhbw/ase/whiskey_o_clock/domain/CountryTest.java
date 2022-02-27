package de.dhbw.ase.whiskey_o_clock.domain;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ValidationException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CountryTest {

    private static final String COUNTRY_NAME = "CountryName";
    private static final String COUNTRY_NAME_NEW = "CountryName NEW";
    private static final String COUNTRY_ABBREVIATION = "TES";
    private static final String COUNTRY_ABBREVIATION_NEW = "TEA";
    private static final String COUNTRY_ABBREVIATION_FAIL = "TEAS";

    private static final UUID COUNTRY_UUID = UUID.randomUUID();
    private static final UUID COUNTRY_UUID_NEW = UUID.randomUUID();

    private Country testCountry;

    @BeforeEach
    public void init() {
        testCountry = new Country(COUNTRY_ABBREVIATION, COUNTRY_NAME);
    }

    @Test
    void getUuid() {
        Country countryMock = Mockito.mock(Country.class);
        when(countryMock.getUuid()).thenReturn(COUNTRY_UUID);
        assertEquals(COUNTRY_UUID, countryMock.getUuid());
    }

    @Test
    void getAbbreviation() {
        assertEquals(COUNTRY_ABBREVIATION, testCountry.getAbbreviation());
    }

    @Test
    void getName() {
        assertEquals(COUNTRY_NAME, testCountry.getName());
    }

    @Test
    void setUuid() {
        testCountry.setUuid(COUNTRY_UUID_NEW);
        assertEquals(COUNTRY_UUID_NEW, testCountry.getUuid());
    }

    @Test
    void setAbbreviation() {
        testCountry.setAbbreviation(COUNTRY_ABBREVIATION_NEW);
        assertEquals(COUNTRY_ABBREVIATION_NEW, testCountry.getAbbreviation());
    }

    @Test
    void setAbbreviationFAIL() {
        Exception ex = assertThrows(ValidationException.class, () -> testCountry.setAbbreviation(COUNTRY_ABBREVIATION_FAIL));

        String expectedMessage = "Abbreviation not valid, only between 1 and 3 are Valid!";
        String actualMessage = ex.getMessage();

        assert (actualMessage.contains(expectedMessage));
    }

    @Test
    void setName() {
        testCountry.setName(COUNTRY_NAME_NEW);
        assertEquals(COUNTRY_NAME_NEW, testCountry.getName());
    }

    @Test
    void testToString() {
        assertEquals("CountryName [TES]", testCountry.toString());
    }
}
