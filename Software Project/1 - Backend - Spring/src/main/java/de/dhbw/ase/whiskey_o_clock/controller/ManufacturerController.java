package de.dhbw.ase.whiskey_o_clock.controller;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

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
    public Manufacturer createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return manufacturerService.createManufacturer(manufacturerDTO);
    }

    @PostMapping(value = "/new", params = {"name", "countryAbbreviation"})
    public Manufacturer createManufacturer(@RequestParam String name, @RequestParam String countryAbbreviation) {
        return manufacturerService.createManufacturer(name, countryAbbreviation);
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
        return manufacturerService.getAllManufacturers();
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
    public Manufacturer updateManufacturer(@RequestParam UUID manufacturerUUID, ManufacturerDTO handOverManufacturerDTO) {
        return manufacturerService.updateManufacturer(manufacturerUUID, handOverManufacturerDTO);
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
        manufacturerService.deleteManufacturerByUUID(uuid);
    }
}
