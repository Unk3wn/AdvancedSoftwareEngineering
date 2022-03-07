package de.dhbw.ase.whiskey_o_clock.plugins.persistence.hibernate.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BottleRepositoryBridge implements BottleRepository {

    private SpringDataBottleRepository springDataBottleRepository;

    @Autowired
    public BottleRepositoryBridge(SpringDataBottleRepository springDataBottleRepository) {
        this.springDataBottleRepository = springDataBottleRepository;
    }

    @Override
    public Bottle save(Bottle bottle) {
        return springDataBottleRepository.save(bottle);
    }

    @Override
    public List<Bottle> findAll() {
        return springDataBottleRepository.findAll();
    }

    @Override
    public List<Bottle> getBottlesByLabel(String bottleLabel) {
        return springDataBottleRepository.getBottlesByLabel(bottleLabel);
    }

    @Override
    public Bottle getFirstBottleByLabelAndManufacturer(String bottleLabel, Manufacturer manufacturer) {
        return springDataBottleRepository.getFirstBottleByLabelAndManufacturer(bottleLabel, manufacturer);
    }

    @Override
    public List<Bottle> getBottlesByLabelAndManufacturer(String bottleLabel, Manufacturer manufacturer) {
        return springDataBottleRepository.getBottlesByLabelAndManufacturer(bottleLabel, manufacturer);
    }

    @Override
    public List<Bottle> getBottlesBySeries(Series series) {
        return springDataBottleRepository.getBottlesBySeries(series);
    }

    @Override
    public Bottle getBottleByUuid(UUID bottleUUID) {
        return springDataBottleRepository.getBottleByUuid(bottleUUID);
    }

    @Override
    public boolean existsById(UUID bottleUUID) {
        return springDataBottleRepository.existsById(bottleUUID);
    }

    @Override
    public boolean existsByLabel(String bottleLabel) {
        return springDataBottleRepository.existsByLabel(bottleLabel);
    }

    @Override
    public boolean existsByLabelAndManufacturer(String bottleLabel, Manufacturer manufacturer) {
        return springDataBottleRepository.existsByLabelAndManufacturer(bottleLabel, manufacturer);
    }

    @Override
    public void delete(Bottle bottleByUUID) {
        springDataBottleRepository.delete(bottleByUUID);
    }
}
