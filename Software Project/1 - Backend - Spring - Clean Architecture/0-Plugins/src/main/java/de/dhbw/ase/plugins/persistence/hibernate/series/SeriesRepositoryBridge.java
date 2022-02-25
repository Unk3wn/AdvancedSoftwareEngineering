package de.dhbw.ase.plugins.persistence.hibernate.series;

import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SeriesRepositoryBridge implements SeriesRepository {

    SpringDataSeriesRepository springDataSeriesRepository;

    @Autowired
    public SeriesRepositoryBridge(SpringDataSeriesRepository springDataSeriesRepository) {
        this.springDataSeriesRepository = springDataSeriesRepository;
    }

    @Override
    public Series save(Series series) {
        return springDataSeriesRepository.save(series);
    }

    @Override
    public Series getSeriesByUuid(UUID seriesUUID) {
        return springDataSeriesRepository.getSeriesByUuid(seriesUUID);
    }

    @Override
    public boolean existsById(UUID seriesUUID) {
        return springDataSeriesRepository.existsById(seriesUUID);
    }

    @Override
    public Series getById(UUID seriesUUID) {
        return springDataSeriesRepository.getSeriesByUuid(seriesUUID);
    }

    @Override
    public List<Series> findAll() {
        return springDataSeriesRepository.findAll();
    }

    @Override
    public void deleteById(UUID seriesUUID) {
        springDataSeriesRepository.deleteById(seriesUUID);
    }
}
