package de.dhbw.ase.whiskey_o_clock;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Country;
import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.Series;
import de.dhbw.ase.whiskey_o_clock.repository.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.repository.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataInserter implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    BottleRepository bottleRepository;
    @Autowired
    SeriesRepository seriesRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Country country = new Country("PAD", "Patricks Länd");
        countryRepository.save(country);

        Manufacturer manufacturer = new Manufacturer("Paddys Whiskey Manufaktur", country);
        manufacturerRepository.save(manufacturer);

        Bottle bottle = new Bottle("Paddys Special Brand", 69.42, 2022, manufacturer);
        bottleRepository.save(bottle);

        Bottle bottle2 = new Bottle("Paddys Special Brand 2", 69.42, 2022, manufacturer);
        bottleRepository.save(bottle2);

        Bottle bottle3 = new Bottle("Paddys Special Brand 3", 69.42, 2022, manufacturer);
        bottleRepository.save(bottle3);

        Series series = new Series("Paddys Best Selection");
        seriesRepository.save(series);

        bottle.setSeries(series);
        bottleRepository.save(bottle);
        bottle2.setSeries(series);
        bottleRepository.save(bottle2);
        bottle3.setSeries(series);
        bottleRepository.save(bottle3);
    }
}
