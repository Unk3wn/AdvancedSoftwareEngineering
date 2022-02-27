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

    private static final UUID COUNTRY_UUID = UUID.randomUUID();
    private static final UUID COUNTRY_UUID_1 = UUID.randomUUID();
    private static final UUID COUNTRY_UUID_2 = UUID.randomUUID();
    private static final UUID COUNTRY_UUID_3 = UUID.randomUUID();
    private static final String COUNTRY_ABBREVIATION = "TES";
    private static final String COUNTRY_NAME = "TestCountry";
    private static final Country COUNTRY_TEST = new Country(COUNTRY_UUID, COUNTRY_ABBREVIATION, COUNTRY_NAME);
    private static final CountryDTO COUNTRY_TEST_DTO = new CountryDTO(COUNTRY_UUID, COUNTRY_ABBREVIATION, COUNTRY_NAME);
    private static final CountryDTO COUNTRY_DTO_1 = new CountryDTO(COUNTRY_UUID_1, "EIN", "EINS");
    private static final CountryDTO COUNTRY_DTO_2 = new CountryDTO(COUNTRY_UUID_2, "ZWE", "ZWEI");
    private static final CountryDTO COUNTRY_DTO_3 = new CountryDTO(COUNTRY_UUID_3, "DRE", "DREI");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CountryController countryController;
    @MockBean
    private CountryApplicationService countryApplicationService;

    @Test
    void createCountry() throws Exception {
        when(countryController.createCountry(COUNTRY_TEST_DTO)).thenReturn(new CountryDTO(COUNTRY_UUID, COUNTRY_ABBREVIATION, COUNTRY_NAME));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/country/")
                        .content(objectMapper.writeValueAsString(COUNTRY_TEST))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        CountryDTO readOutCountry = objectMapper.readValue(responseBody, CountryDTO.class);
        assertEquals(readOutCountry, new CountryDTO(readOutCountry.getUuid(), COUNTRY_ABBREVIATION, COUNTRY_NAME));
    }

    @Test
    void testCreateCountry() throws Exception {
        when(countryController.createCountry(COUNTRY_ABBREVIATION, COUNTRY_NAME)).thenReturn(new CountryDTO(COUNTRY_UUID, COUNTRY_ABBREVIATION, COUNTRY_NAME));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/country/new/" + COUNTRY_ABBREVIATION + "/" + COUNTRY_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        CountryDTO readOutCountry = objectMapper.readValue(responseBody, CountryDTO.class);
        assertEquals(readOutCountry, new CountryDTO(readOutCountry.getUuid(), COUNTRY_ABBREVIATION, COUNTRY_NAME));
    }

    @Test
    void getAllCountrys() throws Exception {
        List<CountryDTO> records = new ArrayList<>(Arrays.asList(COUNTRY_DTO_1, COUNTRY_DTO_2, COUNTRY_DTO_3));
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
        when(countryController.getCountryByAbbreviation(COUNTRY_ABBREVIATION)).thenReturn(COUNTRY_TEST_DTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/country/abbreviation/" + COUNTRY_ABBREVIATION)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals(objectMapper.readValue(responseBody, Country.class).toString(), COUNTRY_TEST.toString());
    }

    @Test
    void updateCountry() throws Exception {
        String newAbbreviation = "CHA";
        String newName = COUNTRY_NAME + "CHANGED";
        CountryDTO changedCountry = new CountryDTO(COUNTRY_UUID, newAbbreviation, newName);

        when(countryController.updateCountry(changedCountry)).thenReturn(new CountryDTO(COUNTRY_UUID, newAbbreviation, newName));
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
    void deleteCountry() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/country/" + COUNTRY_ABBREVIATION)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }


}
