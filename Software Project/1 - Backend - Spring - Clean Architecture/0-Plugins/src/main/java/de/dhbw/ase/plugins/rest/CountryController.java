package de.dhbw.ase.whiskey_o_clock.controller;

import de.dhbw.ase.whiskey_o_clock.application.country.CountryApplicationService;
import de.dhbw.ase.whiskey_o_clock.country.CountryDTO;
import de.dhbw.ase.whiskey_o_clock.country.CountryMapper;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryApplicationService countryApplicationService;

    @Autowired
    private CountryController(CountryApplicationService countryApplicationService){
        this.countryApplicationService = countryApplicationService;
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
    public Country createCountry(@RequestBody CountryDTO handoverCountry) {
        return countryApplicationService.saveCountry(CountryMapper.convertDTOToCountry(handoverCountry));
    }

    @PostMapping(value = "/new", params = {"countryAbbreviation", "countryName"})
    public Country createCountry(@RequestParam String countryAbbreviation, @RequestParam String countryName) {
        return countryApplicationService.saveCountry(countryAbbreviation, countryName);
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
    public List<Country> getAllCountrys() {
        return countryApplicationService.getAllCountrys();
    }

    @GetMapping("/abbreviation")
    public Country getCountryByAbbreviation(@RequestParam String abbreviation) {
        return countryApplicationService.getCountryByAbbreviation(abbreviation);
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
    public Country updateCountry(@RequestParam UUID countryUUID, @RequestBody CountryDTO handOverCountryDTO) {
        return countryApplicationService.updateCountry(countryUUID, CountryMapper.convertDTOToCountry(handOverCountryDTO));
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
    @DeleteMapping("")
    public void deleteCountry(@RequestParam String abbreviation) {
        countryApplicationService.deleteCountry(abbreviation);
    }
}
