package de.dhbw.ase.whiskey_o_clock.model.listener;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.PrePersist;

public class BottleListener {

    private static Log log = LogFactory.getLog(BottleListener.class);

    @PrePersist
    private void beforeAnyUpdate(Bottle bottle) {
        log.info("[BOTTLE AUDIT] About to add a bottle");
    }
}