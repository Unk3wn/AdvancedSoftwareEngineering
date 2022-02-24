package de.dhbw.ase.plugins.persistence.hibernate.manufacturer;

import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ManufacturerRepositoryBridge implements ManufacturerRepository {

    SpringDataManufacturerRepository springDataManufacturerRepository;

    @Autowired
    public ManufacturerRepositoryBridge(SpringDataManufacturerRepository springDataManufacturerRepository){
        this.springDataManufacturerRepository = springDataManufacturerRepository;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return springDataManufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> findAll() {
        return springDataManufacturerRepository.findAll();
    }

    @Override
    public List<Manufacturer> getManufacturerByName(String manufacturerName) {
        return springDataManufacturerRepository.getManufacturerByName(manufacturerName);
    }

    @Override
    public Manufacturer getFirstManufacturerByName(String manufacturerName) {
        return springDataManufacturerRepository.getFirstManufacturerByName(manufacturerName);
    }

    @Override
    public Manufacturer getManufacturerByUuid(UUID manufacturerUUID) {
        return springDataManufacturerRepository.getManufacturerByUuid(manufacturerUUID);
    }

    @Override
    public boolean existsByName(String manufacturerName) {
        return springDataManufacturerRepository.existsByName(manufacturerName);
    }

    @Override
    public boolean existsById(UUID manufacturerUUID) {
        return springDataManufacturerRepository.existsById(manufacturerUUID);
    }

    @Override
    public void deleteByName(String manufacturerName) {
        springDataManufacturerRepository.deleteByName(manufacturerName);
    }

    @Override
    public void deleteById(UUID manufacturerUUID) {
        springDataManufacturerRepository.deleteById(manufacturerUUID);
    }
}
