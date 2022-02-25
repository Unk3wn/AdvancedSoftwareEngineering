package de.dhbw.ase.whiskey_o_clock.controller;

import de.dhbw.ase.whiskey_o_clock.application.country.CountryApplicationService;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTOToCountryMapper;
import de.dhbw.ase.whiskey_o_clock.country.CountryToCountryDTOMapper;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryApplicationService countryApplicationService;

    private CountryDTOToCountryMapper countryDTOToCountryMapper;
    private CountryToCountryDTOMapper countryToCountryDTOMapper;

    @Autowired
    private CountryController(CountryApplicationService countryApplicationService, CountryDTOToCountryMapper countryDTOToCountryMapper,CountryToCountryDTOMapper countryToCountryDTOMapper) {
        this.countryApplicationService = countryApplicationService;
        this.countryDTOToCountryMapper = countryDTOToCountryMapper;
        this.countryToCountryDTOMapper = countryToCountryDTOMapper;
    }

    /************************************************************************************************************************************/
    /*
       _____                _
      / ____|              | |
     | |     _ __ ___  __ _| |_ ___
     | |    | '__/ _ \/ _` | __/ _ \
     | |____| | |  __/ (_| | ||  __/
      \_____|_|  \___|\__,_|\__\___|
     */
    @PostMapping(value = "")
    public CountryDTO createCountry(@RequestBody CountryDTO countryDTO) {
        return countryToCountryDTOMapper.apply(countryApplicationService.saveCountry(countryDTOToCountryMapper.apply(countryDTO)));
    }

    @PostMapping(value = "/new/{countryAbbreviation}/{countryName}")
    public CountryDTO createCountry(@PathVariable("countryAbbreviation") String countryAbbreviation, @PathVariable("countryName") String countryName) {
        return countryToCountryDTOMapper.apply(countryApplicationService.saveCountry(countryAbbreviation, countryName));
    }

    /************************************************************************************************************************************/
    /*
      _____                _
     |  __ \              | |
     | |__) |___  __ _  __| |
     |  _  // _ \/ _` |/ _` |
     | | \ \  __/ (_| | (_| |
     |_|  \_\___|\__,_|\__,_|
     */
    @GetMapping("")
    public List<CountryDTO> getAllCountrys() {
        return countryApplicationService.getAllCountrys().stream().map(countryToCountryDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("/abbreviation/{countryAbbreviation}")
    public CountryDTO getCountryByAbbreviation(@PathVariable("countryAbbreviation") String abbreviation) {
        return countryToCountryDTOMapper.apply(countryApplicationService.getCountryByAbbreviation(abbreviation));
    }

    /************************************************************************************************************************************/
    /*
      _    _           _       _
     | |  | |         | |     | |
     | |  | |_ __   __| | __ _| |_ ___
     | |  | | '_ \ / _` |/ _` | __/ _ \
     | |__| | |_) | (_| | (_| | ||  __/
      \____/| .__/ \__,_|\__,_|\__\___|
            | |
            |_|
     */
    @PutMapping(value = "/edit")
    public CountryDTO updateCountry(@RequestBody CountryDTO countryDTO) {
        return countryToCountryDTOMapper.apply(countryApplicationService.updateCountry(countryDTOToCountryMapper.apply(countryDTO)));
    }

    /************************************************************************************************************************************/
    /*
      _____       _      _
     |  __ \     | |    | |
     | |  | | ___| | ___| |_ ___
     | |  | |/ _ \ |/ _ \ __/ _ \
     | |__| |  __/ |  __/ ||  __/
     |_____/ \___|_|\___|\__\___|
     */
    @DeleteMapping("/{abbreviation}")
    public void deleteCountry(@PathVariable("abbreviation") String abbreviation) {
        countryApplicationService.deleteCountry(abbreviation);
    }
}
