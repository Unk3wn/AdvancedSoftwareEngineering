package de.dhbw.ase.whiskey_o_clock.controller;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.service.BottleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bottle")
public class BottleController {

    private final BottleService bottleService;

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
    public Bottle createBottle(@RequestBody BottleDTO handoverBottle) {
        return bottleService.createBottle(handoverBottle);
    }

    @PostMapping(value = "/new", params = {"bottleLabel", "bottlePrice", "yearOfManufacture", "manufacturerName"})
    public Bottle createBottle(@RequestParam String bottleLabel, @RequestParam double bottlePrice, @RequestParam int yearOfManufacture, @RequestParam String manufacturerName) {
        return bottleService.createBottle(bottleLabel, bottlePrice, yearOfManufacture, manufacturerName);
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
    public List<Bottle> getAllBottles() {
        return bottleService.getBottles();
    }

    @GetMapping("/read/label")
    public List<Bottle> getBottlesByLabel(@RequestParam String label) {
        return bottleService.getBottlesByLabel(label);
    }

    @GetMapping("/read/labelManufacturer")
    public List<Bottle> getBottleByLabelAndManufacturer(@RequestParam String label, @RequestParam String manufacturerName) {
        return bottleService.getBottleByLabelAndManufacturer(label, manufacturerName);
    }

    @GetMapping("/read/uuid")
    public Bottle getBottleByUUID(@RequestParam UUID bottleUUID) {
        return bottleService.getBottleByUUID(bottleUUID);
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
    /************************************************************************************************************************************/
    /*
         _____       _      _
        |  __ \     | |    | |
        | |  | | ___| | ___| |_ ___
        | |  | |/ _ \ |/ _ \ __/ _ \
        | |__| |  __/ |  __/ ||  __/
        |_____/ \___|_|\___|\__\___|
    */
    /************************************************************************************************************************************/
}
