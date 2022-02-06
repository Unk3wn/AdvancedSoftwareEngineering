package de.dhbw.ase.whsikey_o_clock.controller;

import de.dhbw.ase.whsikey_o_clock.model.Country;
import de.dhbw.ase.whsikey_o_clock.model.CountryDTO;
import de.dhbw.ase.whsikey_o_clock.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public List<Country> getAllCountrys() {
        return countryService.getAllCountrys();
    }

    @PutMapping(value = "", params = {"newCountry"})
    public Country newCountry(@RequestBody CountryDTO handoverCountry) {
        return countryService.saveCountry(handoverCountry);
    }

    @PutMapping(value = "/new", params = {"countryAbbreviation", "countryName"})
    public Country newCountry(@RequestParam String countryAbbreviation, @RequestParam String countryName) {
        return countryService.saveCountry(countryAbbreviation, countryName);
    }

    @DeleteMapping("")
    public void delteCountry(@RequestParam String abbreviation) {
        countryService.deleteCountry(abbreviation);
    }
}
