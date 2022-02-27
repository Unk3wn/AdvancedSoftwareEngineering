package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.application.manufacturer.ManufacturerApplicationService;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTO;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerDTOToManufacturer;
import de.dhbw.ase.whiskey_o_clock.manufacturer.ManufacturerToManufacturerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerApplicationService manufacturerApplicationService;

    private ManufacturerDTOToManufacturer manufacturerDTOToManufacturer;
    private ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper;

    @Autowired
    private ManufacturerController(ManufacturerApplicationService manufacturerApplicationService, ManufacturerDTOToManufacturer manufacturerDTOToManufacturer, ManufacturerToManufacturerDTOMapper manufacturerToManufacturerDTOMapper) {
        this.manufacturerApplicationService = manufacturerApplicationService;
        this.manufacturerDTOToManufacturer = manufacturerDTOToManufacturer;
        this.manufacturerToManufacturerDTOMapper = manufacturerToManufacturerDTOMapper;
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
    public ManufacturerDTO createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return manufacturerToManufacturerDTOMapper.apply(manufacturerApplicationService.createManufacturer(manufacturerDTOToManufacturer.apply(manufacturerDTO)));
    }

    @PostMapping(value = "/new", params = {"name", "countryAbbreviation"})
    public ManufacturerDTO createManufacturer(@RequestParam String name, @RequestParam String countryAbbreviation) {
        return manufacturerToManufacturerDTOMapper.apply(manufacturerApplicationService.createManufacturer(name, countryAbbreviation));
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
    public List<ManufacturerDTO> getAllManufacturers() {
        return manufacturerApplicationService.getAllManufacturers().stream().map(manufacturerToManufacturerDTOMapper).collect(Collectors.toList());
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
    public ManufacturerDTO updateManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return manufacturerToManufacturerDTOMapper.apply(manufacturerApplicationService.updateManufacturer(manufacturerDTOToManufacturer.apply(manufacturerDTO)));
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
