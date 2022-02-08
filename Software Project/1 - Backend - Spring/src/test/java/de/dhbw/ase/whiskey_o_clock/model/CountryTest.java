package de.dhbw.ase.whiskey_o_clock.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ValidationException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CountryTest {

    String countryName = "CountryName";
    String countryNameNew = "CountryName NEW";
    String countryAbbreviation = "TES";
    String countryAbbreviationNew = "TEA";
    String countryAbbreviationFAIL = "TEAS";

    UUID countryUUID = UUID.randomUUID();
    UUID countryUUIDNEW = UUID.randomUUID();

    Country testCountry;

    @BeforeEach
    public void init(){
        testCountry = new Country(countryAbbreviation,countryName);
    }

    @Test
    void getUuid() {
        Country countryMock = Mockito.mock(Country.class);
        when(countryMock.getUuid()).thenReturn(countryUUID);
        assertEquals(countryMock.getUuid(),countryUUID);
    }

    @Test
    void getAbbreviation() {
        assertEquals(countryAbbreviation, testCountry.getAbbreviation());
    }

    @Test
    void getName() {
        assertEquals(countryName, testCountry.getName());
    }

    @Test
    void setUuid() {
        testCountry.setUuid(countryUUIDNEW);
        assertEquals(testCountry.getUuid(),countryUUIDNEW);
    }

    @Test
    void setAbbreviation() {
        testCountry.setAbbreviation(countryAbbreviationNew);
        assertEquals(testCountry.getAbbreviation(),countryAbbreviationNew);
    }

    @Test
    void setAbbreviationFAIL() {
        Exception ex = assertThrows(ValidationException.class, () ->  testCountry.setAbbreviation(countryAbbreviationFAIL));

        String expectedMessage = "Abbreviation not valid, only between 1 and 3 are Valid!";
        String actualMessage = ex.getMessage();

        assert(actualMessage.contains(expectedMessage));
    }

    @Test
    void setName() {
        testCountry.setName(countryNameNew);
        assertEquals(testCountry.getName(),countryNameNew);
    }

    @Test
    void testToString() {
        assertEquals("CountryName [TES]",testCountry.toString());
    }
}
