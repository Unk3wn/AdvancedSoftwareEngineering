package de.dhbw.ase.whiskey_o_clock.domain.bottle.listener;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.*;

public class BottleListener {

    private static Log log = LogFactory.getLog(BottleListener.class);

    @PrePersist
    public void logNewAttempt(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Attempting to add a new Bottle with Label '%s'", bottle.getLabel()));
    }

    @PostPersist
    public void logNewAdded(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Added Bottle ['%s'] with Label '%s'", bottle.getUuid(), bottle.getLabel()));
    }

    @PreRemove
    public void logRemovalAttempt(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Attempting to remove Bottle ['%s'] with Label '%s'", bottle.getUuid(), bottle.getLabel()));
    }

    @PostRemove
    public void logRemoval(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Removed Bottle with Label '%s'", bottle.getLabel()));
    }

    @PreUpdate
    public void logUpdateAttempt(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Attempting to update Bottle ['%s'] with Label '%s'", bottle.getUuid(), bottle.getLabel()));
    }

    @PostUpdate
    public void logUpdate(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Updated Bottle ['%s'] with Label '%s'", bottle.getUuid(), bottle.getLabel()));
    }

    @PostLoad
    public void logLoad(Bottle bottle) {
        log.info(String.format("[BOTTLE AUDIT] Loading Bottle ['%s'] with Label '%s'", bottle.getUuid(), bottle.getLabel()));
    }

}
