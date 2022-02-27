package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.whiskey_o_clock.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.application.series.SeriesApplicationService;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.series.SeriesDTOToSeriesMapper;
import de.dhbw.ase.whiskey_o_clock.series.SeriesToSeriesDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesApplicationService seriesApplicationService;

    private SeriesToSeriesDTOMapper seriesToSeriesDTOMapper;
    private SeriesDTOToSeriesMapper seriesDTOToSeriesMapper;

    @Autowired
    private SeriesController(SeriesApplicationService seriesApplicationService, SeriesToSeriesDTOMapper seriesToSeriesDTOMapper, SeriesDTOToSeriesMapper seriesDTOToSeriesMapper) {
        this.seriesApplicationService = seriesApplicationService;
        this.seriesToSeriesDTOMapper = seriesToSeriesDTOMapper;
        this.seriesDTOToSeriesMapper = seriesDTOToSeriesMapper;
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
    public SeriesDTO createSeries(@RequestBody Series series) {
        return seriesToSeriesDTOMapper.apply(seriesApplicationService.createSeries(series));
    }

    @PostMapping(value = "/new", params = {"seriesLabel"})
    public SeriesDTO createSeries(@RequestParam String seriesLabel) {
        return seriesToSeriesDTOMapper.apply(seriesApplicationService.createSeries(seriesLabel, new LinkedList<>()));
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
    public List<SeriesDTO> getAllSeries() {
        return seriesApplicationService.getAllSeries().stream().map(seriesToSeriesDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("/read/uuid")
    public SeriesDTO getSeriesByUUID(@RequestParam UUID uuid) {
        return seriesToSeriesDTOMapper.apply(seriesApplicationService.getSeriesByUUID(uuid));
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
    public SeriesDTO updateSeries(@RequestParam UUID uuid, @RequestParam String newName) {
        return seriesToSeriesDTOMapper.apply(seriesApplicationService.updateSeriesByUUID(uuid, newName));
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
