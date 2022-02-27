package de.dhbw.ase.whiskey_o_clock.application.series;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeriesApplicationService {

    private SeriesRepository seriesRepository;
    private BottleRepository bottleRepository;

    @Autowired
    public SeriesApplicationService(BottleRepository bottleRepository, SeriesRepository seriesRepository) {
        this.bottleRepository = bottleRepository;
        this.seriesRepository = seriesRepository;
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
    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series createSeries(String label, List<Bottle> bottleList) {
        // kein Check, da mehrere Serien mit dem Selben Namen vorhanden sein dürfen
        return createSeries(new Series(label, bottleList));
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
    public Series getSeriesByUUID(UUID uuid) {
        return seriesRepository.getById(uuid);
    }

    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
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
    public Series updateSeriesByUUID(UUID uuid, String newName) {
        Series foundSeries = seriesRepository.getById(uuid);
        foundSeries.setLabel(newName);
        seriesRepository.save(foundSeries);
        return foundSeries;
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
    public void deleteSeries(UUID uuid) {
        //Remove Series from Bottles#
        if (seriesRepository.existsById(uuid)) {
            Series targetSeries = seriesRepository.getSeriesByUuid(uuid);
            List<Bottle> bottleList = bottleRepository.getBottlesBySeries(targetSeries);
            if (null != bottleList) {
                for (Bottle bottle : bottleList) {
                    bottle.setSeries(null);
                    bottleRepository.save(bottle);
                }
                seriesRepository.deleteById(uuid);
            }
        }
    }
    /************************************************************************************************************************************/


}
