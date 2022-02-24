package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.application.series.SeriesApplicationService;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.series.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.series.SeriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesApplicationService seriesApplicationService;

    @Autowired
    private SeriesController(SeriesApplicationService seriesApplicationService){
        this.seriesApplicationService = seriesApplicationService;
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
    public Series createSeries(@RequestBody Series series) {
        return seriesApplicationService.createSeries(series);
    }
    @PostMapping(value = "/new", params = {"seriesLabel"})
    public Series createSeries(@RequestParam String seriesLabel) {
        return seriesApplicationService.createSeries(seriesLabel, new LinkedList<>());
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
        return seriesApplicationService.getAllSeries();
    }

    @GetMapping("/read/uuid")
    public Series getSeriesByUUID(@RequestParam UUID uuid) {
        return seriesApplicationService.getSeriesByUUID(uuid);
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
        return seriesApplicationService.updateSeriesByUUID(uuid, newName);
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
        seriesApplicationService.deleteSeries(uuid);
    }
    /************************************************************************************************************************************/
}
