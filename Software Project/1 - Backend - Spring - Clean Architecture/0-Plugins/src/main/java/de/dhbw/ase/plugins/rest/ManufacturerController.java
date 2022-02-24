package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.application.manufacturer.ManufacturerApplicationService;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerApplicationService manufacturerApplicationService;

    @Autowired
    private ManufacturerController(ManufacturerApplicationService manufacturerApplicationService){
        this.manufacturerApplicationService = manufacturerApplicationService;
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
    public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer) {
        return manufacturerApplicationService.createManufacturer(manufacturer);
    }
    @PostMapping(value = "/new", params = {"name", "countryAbbreviation"})
    public Manufacturer createManufacturer(@RequestParam String name, @RequestParam String countryAbbreviation) {
        return manufacturerApplicationService.createManufacturer(name, countryAbbreviation);
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
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerApplicationService.getAllManufacturers();
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
    public Manufacturer updateManufacturer(@RequestBody Manufacturer manufacturer) {
        return manufacturerApplicationService.updateManufacturer(manufacturer);
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
    public void deleteManufacturer(@RequestParam UUID uuid) {
        manufacturerApplicationService.deleteManufacturerByUUID(uuid);
    }
}
