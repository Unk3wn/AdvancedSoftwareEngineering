package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.application.bottle.BottleApplicationService;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTOToBottleMapper;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bottle")
public class BottleController {

    private BottleApplicationService bottleApplicationService;
    private BottleDTOToBottleMapper bottleDTOToBottleMapper;

    @Autowired
    private BottleController(BottleApplicationService bottleApplicationService,BottleDTOToBottleMapper bottleDTOToBottleMapper) {
        this.bottleApplicationService = bottleApplicationService;
        this.bottleDTOToBottleMapper = bottleDTOToBottleMapper;
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
    public Bottle createBottle(@RequestBody BottleDTO bottleDTO) {
        return bottleApplicationService.createBottle(bottleDTOToBottleMapper.apply(bottleDTO));
    }

    @PostMapping(value = "/new", params = {"bottleLabel", "bottlePrice", "yearOfManufacture", "manufacturerName"})
    public Bottle createBottle(@RequestParam String bottleLabel, @RequestParam double bottlePrice, @RequestParam int yearOfManufacture, @RequestParam String manufacturerName) {
        return bottleApplicationService.createBottle(bottleLabel, bottlePrice, yearOfManufacture, manufacturerName);
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
        return bottleApplicationService.getBottles();
    }

    @GetMapping("/read/label")
    public List<Bottle> getBottlesByLabel(@RequestParam String label) {
        return bottleApplicationService.getBottlesByLabel(label);
    }

    @GetMapping("/read/uuid")
    public Bottle getBottleByUUID(@RequestParam UUID bottleUUID) {
        return bottleApplicationService.getBottleByUUID(bottleUUID);
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
    public Bottle updateBottle(@RequestBody BottleDTO bottleDTO) {
        return bottleApplicationService.updateBottle(bottleDTOToBottleMapper.apply(bottleDTO));
    }

    @PutMapping(value = "/edit/forSale")
    public Bottle updateBottleForSale(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleApplicationService.updateBottleForSale(uuid, newValue);
    }

    @PutMapping(value = "/edit/favorite")
    public Bottle updateBottleFavorite(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleApplicationService.updateBottleFavorite(uuid, newValue);
    }

    @PutMapping(value = "/edit/unsaleable")
    public Bottle updateBottleUnsaleable(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleApplicationService.updateBottleUnsaleable(uuid, newValue);
    }

    @PutMapping(value = "/edit/series")
    public Series updateSeriesForBottle(@RequestParam UUID bottleUUID, @RequestParam UUID seriesUUID) {
        return bottleApplicationService.updateBottleSeries(bottleUUID, seriesUUID);
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
    public void deleteBottle(@RequestParam UUID uuid) {
        bottleApplicationService.deleteBottleByUUID(uuid);
    }
    /************************************************************************************************************************************/
}
