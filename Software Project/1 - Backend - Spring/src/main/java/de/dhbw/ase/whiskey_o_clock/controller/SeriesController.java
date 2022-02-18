package de.dhbw.ase.whiskey_o_clock.controller;

import de.dhbw.ase.whiskey_o_clock.model.Series;
import de.dhbw.ase.whiskey_o_clock.model.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.service.BottleService;
import de.dhbw.ase.whiskey_o_clock.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */

    @PostMapping(value = "/new", params = {"seriesLabel"})
    public Series createSeries(@RequestParam String seriesLabel) {
        return seriesService.createSeries(seriesLabel, new LinkedList<>());
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
    public List<Series> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/read/uuid")
    public Series getSeriesByUUID(@RequestParam UUID uuid) {
        return seriesService.getSeriesByUUID(uuid);
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
    @PutMapping(value = "")
    public Series updateSeries(@RequestParam UUID uuid, @RequestParam String newName) {
        return seriesService.updateSeriesByUUID(uuid, newName);
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
    public void deleteSeries(@RequestParam UUID uuid) {
        seriesService.deleteSeries(uuid);
    }
    /************************************************************************************************************************************/
}
