package de.dhbw.ase.plugins.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dhbw.ase.whiskey_o_clock.application.country.CountryApplicationService;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = de.dhbw.ase.whiskey_o_clock.WhsikeyOClockApplication.class)
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    de.dhbw.ase.whiskey_o_clock.controller.CountryController countryController;

    @MockBean
    CountryApplicationService countryApplicationService;

    UUID countryUUID = UUID.randomUUID();
    UUID countryUUID1 = UUID.randomUUID();
    UUID countryUUID2 = UUID.randomUUID();
    UUID countryUUID3 = UUID.randomUUID();
    String countryAbbreviation = "TES";
    String countryName = "Testcountry";

    Country countryTest = new Country(countryUUID, countryAbbreviation, countryName);
    CountryDTO countryTestDTO = new CountryDTO(countryUUID, countryAbbreviation, countryName);

    CountryDTO c1 = new CountryDTO(countryUUID1,"EIN", "EINS");
    CountryDTO c2 = new CountryDTO(countryUUID2,"ZWE", "ZWEI");
    CountryDTO c3 = new CountryDTO(countryUUID3,"DRE", "DREI");

    @Test
    void createCountry() throws Exception {
        when(countryController.createCountry(countryTestDTO)).thenReturn(new CountryDTO(countryUUID, countryAbbreviation, countryName));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/country/")
                        .content(objectMapper.writeValueAsString(countryTest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        CountryDTO readOutCountry = objectMapper.readValue(responseBody, CountryDTO.class);
        assertEquals(readOutCountry, new CountryDTO(readOutCountry.getUuid(), countryAbbreviation, countryName));
    }

    @Test
    void testCreateCountry() throws Exception {
        when(countryController.createCountry(countryAbbreviation, countryName)).thenReturn(new CountryDTO(countryUUID, countryAbbreviation, countryName));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/country/new")
                        .param("countryAbbreviation", countryAbbreviation)
                        .param("countryName", countryName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        CountryDTO readOutCountry = objectMapper.readValue(responseBody, CountryDTO.class);
        assertEquals(readOutCountry, new CountryDTO(readOutCountry.getUuid(), countryAbbreviation, countryName));
    }

    @Test
    void getAllCountrys() throws Exception {
        List<CountryDTO> records = new ArrayList<>(Arrays.asList(c1, c2, c3));
        when(countryController.getAllCountrys()).thenReturn(records);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/country/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(Arrays.toString(objectMapper.readValue(responseBody, CountryDTO[].class)), Arrays.toString(records.toArray()));
    }

    @Test
    void getCountryByAbbreviation() throws Exception {
        when(countryController.getCountryByAbbreviation(countryAbbreviation)).thenReturn(countryTestDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/country/abbreviation/")
                        .param("abbreviation", countryAbbreviation)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.readValue(responseBody, Country.class).toString(), countryTest.toString());
    }

    @Test
    void updateCountry() throws Exception {
        String newAbbreviation = "CHA";
        String newName = countryName + "CHANGED";
        CountryDTO changedCountry = new CountryDTO(countryUUID, newAbbreviation, newName);

        when(countryController.updateCountry(changedCountry)).thenReturn(new CountryDTO(countryUUID, newAbbreviation,newName));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/country/edit/")
                        .content(objectMapper.writeValueAsString(changedCountry))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.readValue(responseBody, CountryDTO.class).toString(), changedCountry.toString());
    }


    @Test
    void delteCountry() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/country")
                        .param("abbreviation", countryAbbreviation)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }


}
