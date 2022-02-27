package de.dhbw.ase.whiskey_o_clock;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import de.dhbw.ase.whiskey_o_clock.domain.country.CountryRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// Should be Commented out for PROD Use -> only for DEV Purposes
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

        Country uk = new Country("UK", "Vereinigtes Königreich");
        countryRepository.save(uk);

        Manufacturer ardbeg = new Manufacturer("Ardbeg", uk);
        Manufacturer lagavulin = new Manufacturer("Brennerei Lagavulin", uk);
        manufacturerRepository.save(ardbeg);
        manufacturerRepository.save(lagavulin);

        Bottle ardbeg10 = new Bottle("Ten 10 Jahre Islay Single Malt Scotch Whisky", 60.06, 2012, ardbeg);
        ardbeg10.setForSale(true);
        bottleRepository.save(ardbeg10);

        Bottle uigeadail = new Bottle("Uigeadail", 62.87, 2022, ardbeg);
        uigeadail.setFavorite(true);
        bottleRepository.save(uigeadail);

        Bottle specialRelease26 = new Bottle("26 Jahre Special Release", 1999.0, 1994, lagavulin);
        specialRelease26.setUnsaleable(true);
        bottleRepository.save(specialRelease26);

        Bottle destillersEdition2021 = new Bottle("Distillers Edition 2021", 99.99, 2006, lagavulin);
        destillersEdition2021.setForSale(true);
        bottleRepository.save(destillersEdition2021);

        Bottle destillersEdition2020 = new Bottle("Distillers Edition 2020", 157.99, 2005, lagavulin);
        destillersEdition2020.setForSale(true);
        bottleRepository.save(destillersEdition2020);

        Series destillersEdition = new Series("Lagavulin Destillers Edition");
        seriesRepository.save(destillersEdition);

        destillersEdition2020.setSeries(destillersEdition);
        bottleRepository.save(destillersEdition2020);
        destillersEdition2021.setSeries(destillersEdition);
        bottleRepository.save(destillersEdition2021);
    }
}
