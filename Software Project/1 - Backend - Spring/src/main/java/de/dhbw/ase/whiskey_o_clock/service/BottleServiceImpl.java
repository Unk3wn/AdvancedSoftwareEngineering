package de.dhbw.ase.whiskey_o_clock.service;

import de.dhbw.ase.whiskey_o_clock.helper.BottleBooleanType;
import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.BottleDTO;
import de.dhbw.ase.whiskey_o_clock.repository.BottleRepository;
import de.dhbw.ase.whiskey_o_clock.repository.ManufacturerRepository;
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
        return createBottle(bottleDTO.getLabel(), bottleDTO.getPrice(), bottleDTO.getYearOfManufacture(), bottleDTO.getManufacturerName());
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
            Bottle targetBottle = getBottleByLabelAndManufacturer(label, manufacturerName);
            if (null != targetBottle && targetBottle.getYearOfManufacture() == yearOfManufacture) {
                throw new ValidationException(String.format("Bottle '%s' with the Manufacturer '%s' and the Year of Manufactoring '%d' is already in the Database!", targetBottle.getLabel(), manufacturerName, targetBottle.getYearOfManufacture()));
            }
            Bottle newBottle = new Bottle(label, price, yearOfManufacture, manufacturerRepository.getManufacturerByName(manufacturerName), isForSale, isFavorite, isUnsaleable);
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
    public List<Bottle> getBottles() {
        return bottleRepository.findAll();
    }

    @Override
    public List<Bottle> getBottlesByLabel(String label) {
        return bottleRepository.getBottlesByLabel(label);
    }

    @Override
    public Bottle getBottleByLabelAndManufacturer(String label, String manufacturerName) {
        return bottleRepository.getBottleByLabelAndManufacturer(label, manufacturerRepository.getManufacturerByName(manufacturerName));
    }

    @Override
    public Bottle getBottleByUUID(UUID uuid) {
        return bottleRepository.getBottleByUuid(uuid);
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
        if (null != bottleRepository.getBottleByUuid(bottleUUID)) {
            Bottle foundBottle = bottleRepository.getBottleByUuid(bottleUUID);
            foundBottle.updateFromDTO(bottleDTO, manufacturerRepository.getManufacturerByName(bottleDTO.getManufacturerName()));
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

    private Bottle updateBooleanBottleValue(UUID bottleUUID, Boolean value, BottleBooleanType type) {
        if (null != bottleRepository.getBottleByUuid(bottleUUID)) {
            Bottle foundBottle = bottleRepository.getBottleByUuid(bottleUUID);

            if (type.equals(BottleBooleanType.FORSALE)) {
                foundBottle.setForSale(value);
            }
            if (type.equals(BottleBooleanType.FAVORITE)) {
                foundBottle.setFavorite(value);
            }
            if (type.equals(BottleBooleanType.UNSALEABLE)) {
                foundBottle.setUnsaleable(value);
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
    /************************************************************************************************************************************/
}
