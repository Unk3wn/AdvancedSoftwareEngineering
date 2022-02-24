package de.dhbw.ase.whiskey_o_clock.application.bottle;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.builder.BottleBuilder;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import de.dhbw.ase.whiskey_o_clock.domain.bottle.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.domain.series.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class BottleApplicationService{

    private BottleRepository bottleRepository;
    private ManufacturerRepository manufacturerRepository;
    private SeriesRepository seriesRepository;

    @Autowired
    public BottleApplicationService(ManufacturerRepository manufacturerRepository,BottleRepository bottleRepository,SeriesRepository seriesRepository){
        this.bottleRepository = bottleRepository;
        this.manufacturerRepository = manufacturerRepository;
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

    public Bottle createBottle (Bottle bottle){
       return bottleRepository.save(bottle);
    }

    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false);
    }

    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false, false);
    }

    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale, boolean isFavorite) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false, false, false);
    }

    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale, boolean isFavorite, boolean isUnsaleable) {
        if (bottleRepository.existsByLabelAndManufacturer(label, manufacturerRepository.getFirstManufacturerByName(manufacturerName))) {
            throw new ValidationException(String.format("Bottle '%s' with the Manufacturer '%s' already in Database!", label, manufacturerName));
        }
        if(!manufacturerRepository.existsByName(manufacturerName)){
            throw new ValidationException(String.format("Manufacturer '%s' not known!", manufacturerName));
        }
        Bottle newBottle = new BottleBuilder(label)
            .price(price)
            .yearOfManufacture(yearOfManufacture)
            .manufacturer(manufacturerRepository.getFirstManufacturerByName(manufacturerName))
            .forSale(isForSale)
            .favorite(isFavorite)
            .unsaleable(isUnsaleable)
            .build();
        bottleRepository.save(newBottle);
        return newBottle;
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
    public Bottle getBottleByUUID(UUID uuid) {
        return bottleRepository.getBottleByUuid(uuid);
    }

    public List<Bottle> getBottles() {
        return bottleRepository.findAll();
    }

    public List<Bottle> getBottlesByLabel(String label) {
        return bottleRepository.getBottlesByLabel(label);
    }

    public List<Bottle> getBottleByLabelAndManufacturer(String label, String manufacturerName) {
        return bottleRepository.getBottlesByLabelAndManufacturer(label, manufacturerRepository.getFirstManufacturerByName(manufacturerName));
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
    public Bottle updateBottle(UUID uuid, Bottle convertDTOToBottle) {
        return null;
    }
    public Bottle updateBottleForSale(UUID bottleUUID, Boolean isForSale) {
        return updateBooleanBottleValue(bottleUUID, isForSale, BottleBooleanType.FORSALE);
    }

    public Bottle updateBottleFavorite(UUID bottleUUID, Boolean isFavorite) {
        return updateBooleanBottleValue(bottleUUID, isFavorite, BottleBooleanType.FAVORITE);
    }

    public Bottle updateBottleUnsaleable(UUID bottleUUID, Boolean isUnsaleable) {
        return updateBooleanBottleValue(bottleUUID, isUnsaleable, BottleBooleanType.UNSALEABLE);
    }

    public Series updateBottleSeries(UUID bottleUUID, UUID seriesUUID) {
        if (bottleRepository.existsById(bottleUUID)) {
            if(seriesRepository.existsById(seriesUUID)){
                Bottle bottle = bottleRepository.getBottleByUuid(bottleUUID);
                bottle.setSeries(seriesRepository.getById(seriesUUID));
                return bottle.getSeries();
            }
            throw new ValidationException("Series not found!");
        }
        throw new ValidationException("Bottle not found!");
    }

    private Bottle updateBooleanBottleValue(UUID bottleUUID, Boolean value, BottleBooleanType type) {
        if (bottleRepository.existsById(bottleUUID)) {
            Bottle foundBottle = bottleRepository.getBottleByUuid(bottleUUID);
            switch (type) {
                case FORSALE:
                    foundBottle.setForSale(value);
                    break;
                case FAVORITE:
                    foundBottle.setFavorite(value);
                    break;
                case UNSALEABLE:
                    foundBottle.setUnsaleable(value);
                    break;
            }
            bottleRepository.save(foundBottle);
            return foundBottle;
        }
        throw new ValidationException("UUID is not known");


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
    public void deleteBottleByUUID(UUID bottleUUID) {
        bottleRepository.delete(getBottleByUUID(bottleUUID));
    }

    public Bottle deleteSeriesFromBottleByUUID(UUID bottleUUID) {
        if(bottleRepository.existsById(bottleUUID)){
            Bottle tempBottle = bottleRepository.getBottleByUuid(bottleUUID);
            tempBottle.setSeries(null);
            bottleRepository.save(tempBottle);
            return tempBottle;
        }
        throw new ValidationException("Bottle not found!");
    }
    /************************************************************************************************************************************/
}
