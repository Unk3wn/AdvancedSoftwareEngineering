package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Series;
import de.dhbw.ase.whiskey_o_clock.model.SeriesDTO;
import de.dhbw.ase.whiskey_o_clock.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    SeriesRepository seriesRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    @Override
    public Series createSeries(SeriesDTO seriesDTO) {
        return createSeries(seriesDTO.getSeriesLabel(),seriesDTO.getSeriesBottleList());
    }

    @Override
    public Series createSeries(String label, List<Bottle> bottleList) {
        // kein Check, da mehrere Serien mit dem Selben Namen vorhanden sein dürfen
        Series newSeries = new Series(label,bottleList);
        seriesRepository.save(newSeries);
        return newSeries;
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
    @Override
    public Series getSeriesByUUID(UUID uuid) {
        return seriesRepository.getById(uuid);
    }

    @Override
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
    @Override
    public Series updateSeriesByUUID(UUID uuid,SeriesDTO seriesDTO) {
        Series foundSeries = seriesRepository.getById(uuid);
        foundSeries.updateFromDTO(seriesDTO);
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
    @Override
    public void deleteSeries(UUID uuid) {
        seriesRepository.deleteById(uuid);
    }
    /************************************************************************************************************************************/


}
