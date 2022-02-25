package de.dhbw.ase.plugins.persistence.hibernate.series;

import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSeriesRepository extends JpaRepository<Series, UUID> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Series getSeriesByUuid(UUID seriesUUID);

    /** UPDATE **/

    /** DELETE **/

}
