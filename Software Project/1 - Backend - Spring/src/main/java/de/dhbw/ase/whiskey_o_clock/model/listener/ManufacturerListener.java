package de.dhbw.ase.whiskey_o_clock.model.listener;

import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.*;

public class ManufacturerListener {

    private static Log log = LogFactory.getLog(ManufacturerListener.class);

    @PrePersist
    public void logNewAttempt(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Attempting to add a new Manufacturer with Name '%s'",manufacturer.getName()));
    }

    @PostPersist
    public void logNewAdded(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Added Manufacturer ['%s'] with Name '%s'",manufacturer.getUuid(),manufacturer.getName()));
    }

    @PreRemove
    public void logRemovalAttempt(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Attempting to remove Manufacturer ['%s'] with Name '%s'",manufacturer.getUuid(),manufacturer.getName()));
    }

    @PostRemove
    public void logRemoval(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Removed Manufacturer with Name '%s'",manufacturer.getName()));
    }

    @PreUpdate
    public void logUpdateAttempt(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Attempting to update Manufacturer ['%s'] with Name '%s'",manufacturer.getUuid(),manufacturer.getName()));
    }

    @PostUpdate
    public void logUpdate(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Updated Manufacturer ['%s'] with Name '%s'",manufacturer.getUuid(),manufacturer.getName()));
    }

    @PostLoad
    public void logLoad(Manufacturer manufacturer) {
        log.info(String.format("[MANUFACTURER AUDIT] Loading Manufacturer ['%s'] with Name '%s'",manufacturer.getUuid(),manufacturer.getName()));
    }

}
