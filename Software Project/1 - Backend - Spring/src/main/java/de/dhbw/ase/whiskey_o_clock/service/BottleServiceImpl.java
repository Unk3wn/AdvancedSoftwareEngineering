package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.helper.BottleBooleanType;
import de.dhbw.ase.whiskey_o_clock.helper.DTOMapper;
import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.model.Series;
import de.dhbw.ase.whiskey_o_clock.repository.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
import de.dhbw.ase.whiskey_o_clock.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@Service
public class BottleServiceImpl implements BottleService {

    @Autowired
    BottleRepository bottleRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

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
    public Bottle createBottle(BottleDTO bottleDTO) {
        return createBottle(bottleDTO.getLabel(), bottleDTO.getPrice(), bottleDTO.getYearOfManufacture(), manufacturerRepository.getManufacturerByUuid(bottleDTO.getManufacturer()).getName());
    }

    @Override
    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false);
    }

    @Override
    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false, false);
    }

    @Override
    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale, boolean isFavorite) {
        return createBottle(label, price, yearOfManufacture, manufacturerName, false, false, false);
    }

    @Override
    public Bottle createBottle(String label, double price, int yearOfManufacture, String manufacturerName, boolean isForSale, boolean isFavorite, boolean isUnsaleable) {
        if (null != getBottlesByLabel(label)) {
            if (null != getBottleByLabelAndManufacturer(label, manufacturerName)) {
                throw new ValidationException(String.format("Bottle '%s' with the Manufacturer '%s' already in Database!", label, manufacturerName));
            }
            Bottle targetBottle = bottleRepository.getFirstBottleByLabelAndManufacturer(label, manufacturerRepository.getFirstManufacturerByName(manufacturerName));
            if (null != targetBottle && targetBottle.getYearOfManufacture() == yearOfManufacture) {
                throw new ValidationException(String.format("Bottle '%s' with the Manufacturer '%s' and the Year of Manufactoring '%d' is already in the Database!", targetBottle.getLabel(), manufacturerName, targetBottle.getYearOfManufacture()));
            }
            Bottle newBottle = new Bottle(label, price, yearOfManufacture, manufacturerRepository.getFirstManufacturerByName(manufacturerName), null,isForSale, isFavorite, isUnsaleable);
            bottleRepository.save(newBottle);
            return newBottle;
        }
        throw new ValidationException(String.format("Bottle '%s' not found!", label));
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
    public Bottle getBottleByUUID(UUID uuid) {
        return bottleRepository.getBottleByUuid(uuid);
    }

    @Override
    public List<Bottle> getBottles() {
        return bottleRepository.findAll();
    }

    @Override
    public List<Bottle> getBottlesByLabel(String label) {
        return bottleRepository.getBottlesByLabel(label);
    }

    @Override
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
    @Override
    public Bottle updateBottle(UUID bottleUUID, BottleDTO bottleDTO) {
        if (bottleRepository.existsById(bottleUUID)) {
            Bottle foundBottle = bottleRepository.getBottleByUuid(bottleUUID);
            DTOMapper.updateBottleWithDTO(foundBottle, bottleDTO);
            bottleRepository.save(foundBottle);
            return foundBottle;
        }
        throw new ValidationException("UUID is not known");
    }

    @Override
    public Bottle updateBottleForSale(UUID bottleUUID, Boolean isForSale) {
        return updateBooleanBottleValue(bottleUUID, isForSale, BottleBooleanType.FORSALE);
    }

    @Override
    public Bottle updateBottleFavorite(UUID bottleUUID, Boolean isFavorite) {
        return updateBooleanBottleValue(bottleUUID, isFavorite, BottleBooleanType.FAVORITE);
    }

    @Override
    public Bottle updateBottleUnsaleable(UUID bottleUUID, Boolean isUnsaleable) {
        return updateBooleanBottleValue(bottleUUID, isUnsaleable, BottleBooleanType.UNSALEABLE);
    }

    @Override
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
    @Override
    public void deleteBottleByUUID(UUID bottleUUID) {
        bottleRepository.delete(getBottleByUUID(bottleUUID));
    }

    @Override
    public BottleDTO deleteSeriesFromBottleByUUID(UUID bottleUUID) {
        if(bottleRepository.existsById(bottleUUID)){
            Bottle tempBottle = bottleRepository.getBottleByUuid(bottleUUID);
            tempBottle.setSeries(null);
            bottleRepository.save(tempBottle);
            return DTOMapper.convertBottleToDTO(tempBottle);
        }
        throw new ValidationException("Bottle not found!");
    }
    /************************************************************************************************************************************/
}
