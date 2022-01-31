package de.dhbw.ase.whsikey_o_clock.controller;

import de.dhbw.ase.whsikey_o_clock.model.Bottle;
import de.dhbw.ase.whsikey_o_clock.model.Country;
import de.dhbw.ase.whsikey_o_clock.service.BottleService;
import de.dhbw.ase.whsikey_o_clock.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("")
    public List<Country> getAllCountrys(){
        return countryService.getAllCountrys();
    }

    @PostMapping("")
    public Country newCountry(@RequestBody Country newCountry) {
        return countryService.createCountry(newCountry.getAbbreviation(), newCountry.getName());
    }

    @DeleteMapping("")
    public void delteCountry(@RequestParam String abbreviation){
        countryService.deleteCountry(abbreviation);
    }
}
