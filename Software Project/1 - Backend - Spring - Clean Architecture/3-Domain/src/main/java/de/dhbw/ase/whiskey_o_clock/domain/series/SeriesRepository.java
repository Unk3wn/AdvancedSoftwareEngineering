package de.dhbw.ase.whiskey_o_clock.domain.series;

import java.util.List;
import java.util.UUID;

public interface SeriesRepository {

    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/
    Series save(Series series);

    /** READ **/
    Series getSeriesByUuid(UUID seriesUUID);
    boolean existsById(UUID seriesUUID);
    Series getById(UUID seriesUUID);
    List<Series> findAll();




    /** UPDATE **/

    /** DELETE **/
    void deleteById(UUID seriesUUID);
}
