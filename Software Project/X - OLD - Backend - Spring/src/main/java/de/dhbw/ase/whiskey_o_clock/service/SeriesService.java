package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Series;
import de.dhbw.ase.whiskey_o_clock.model.SeriesDTO;

import java.util.List;
import java.util.UUID;

public interface SeriesService {

    /**
     * CREATE
     **/
    Series createSeries(SeriesDTO seriesDTO);
    Series createSeries(String label, List<Bottle> bottleList);

    /**
     * READ
     **/
    Series getSeriesByUUID(UUID uuid);

    List<Series> getAllSeries();

    /**
     * UPDATE
     **/
    Series updateSeriesByUUID(UUID uuid, String newName);

    /**
     * DELETE
     **/
    void deleteSeries(UUID uuid);


}
