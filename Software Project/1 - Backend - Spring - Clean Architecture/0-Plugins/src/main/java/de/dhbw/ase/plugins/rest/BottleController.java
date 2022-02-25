package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.application.bottle.BottleApplicationService;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleDTOToBottleMapper;
import de.dhbw.ase.whiskey_o_clock.bottle.BottleToBottleDTOMapper;
import de.dhbw.ase.whiskey_o_clock.series.SeriesToSeriesDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bottle")
public class BottleController {

    private BottleApplicationService bottleApplicationService;
    private BottleDTOToBottleMapper bottleDTOToBottleMapper;
    private BottleToBottleDTOMapper bottleToBottleDTOMapper;
    private SeriesToSeriesDTOMapper seriesToSeriesDTOMapper;

    @Autowired
    private BottleController(BottleApplicationService bottleApplicationService,BottleDTOToBottleMapper bottleDTOToBottleMapper,BottleToBottleDTOMapper bottleToBottleDTOMapper,SeriesToSeriesDTOMapper seriesToSeriesDTOMapper) {
        this.bottleApplicationService = bottleApplicationService;
        this.bottleDTOToBottleMapper = bottleDTOToBottleMapper;
        this.bottleToBottleDTOMapper = bottleToBottleDTOMapper;
        this.seriesToSeriesDTOMapper = seriesToSeriesDTOMapper;
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
    public BottleDTO createBottle(@RequestBody BottleDTO bottleDTO) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.createBottle(bottleDTOToBottleMapper.apply(bottleDTO)));
    }

    @PostMapping(value = "/new", params = {"bottleLabel", "bottlePrice", "yearOfManufacture", "manufacturerName"})
    public BottleDTO createBottle(@RequestParam String bottleLabel, @RequestParam double bottlePrice, @RequestParam int yearOfManufacture, @RequestParam String manufacturerName) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.createBottle(bottleLabel, bottlePrice, yearOfManufacture, manufacturerName));
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
    public List<BottleDTO> getAllBottles() {
        return bottleApplicationService.getBottles().stream().map(bottleToBottleDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("/read/label")
    public List<BottleDTO> getBottlesByLabel(@RequestParam String label) {
        return bottleApplicationService.getBottlesByLabel(label).stream().map(bottleToBottleDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("/read/uuid")
    public BottleDTO getBottleByUUID(@RequestParam UUID bottleUUID) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.getBottleByUUID(bottleUUID));
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
    public BottleDTO updateBottle(@RequestBody BottleDTO bottleDTO) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.updateBottle(bottleDTOToBottleMapper.apply(bottleDTO)));
    }

    @PutMapping(value = "/edit/forSale")
    public BottleDTO updateBottleForSale(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.updateBottleForSale(uuid, newValue));
    }

    @PutMapping(value = "/edit/favorite")
    public BottleDTO updateBottleFavorite(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.updateBottleFavorite(uuid, newValue));
    }

    @PutMapping(value = "/edit/unsaleable")
    public BottleDTO updateBottleUnsaleable(@RequestParam UUID uuid, @RequestParam boolean newValue) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.updateBottleUnsaleable(uuid, newValue));
    }

    @PutMapping(value = "/edit/series")
    public BottleDTO updateSeriesForBottle(@RequestParam UUID bottleUUID, @RequestParam UUID seriesUUID) {
        return bottleToBottleDTOMapper.apply(bottleApplicationService.updateBottleSeries(bottleUUID, seriesUUID));
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
