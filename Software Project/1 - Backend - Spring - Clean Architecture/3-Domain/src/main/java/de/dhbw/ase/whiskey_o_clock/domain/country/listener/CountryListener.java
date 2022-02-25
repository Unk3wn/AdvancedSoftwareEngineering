package de.dhbw.ase.whiskey_o_clock.domain.country.listener;

import de.dhbw.ase.whiskey_o_clock.domain.country.Country;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.*;

public class CountryListener {

    private static Log log = LogFactory.getLog(CountryListener.class);

    @PrePersist
    public void logNewAttempt(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Attempting to add a new Country with Abbreviation '%s'", country.getAbbreviation()));
    }

    @PostPersist
    public void logNewAdded(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Added Country ['%s'] with Abbreviation '%s'", country.getUuid(), country.getAbbreviation()));
    }

    @PreRemove
    public void logRemovalAttempt(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Attempting to remove Country ['%s'] with Abbreviation '%s'", country.getUuid(), country.getAbbreviation()));
    }

    @PostRemove
    public void logRemoval(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Removed Country with Abbreviation '%s'", country.getAbbreviation()));
    }

    @PreUpdate
    public void logUpdateAttempt(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Attempting to update Country ['%s'] with Abbreviation '%s'", country.getUuid(), country.getAbbreviation()));
    }

    @PostUpdate
    public void logUpdate(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Updated Country ['%s'] with Abbreviation '%s'", country.getUuid(), country.getAbbreviation()));
    }

    @PostLoad
    public void logLoad(Country country) {
        log.info(String.format("[COUNTRY AUDIT] Loading Country ['%s'] with Abbreviation '%s'", country.getUuid(), country.getAbbreviation()));
    }

}
