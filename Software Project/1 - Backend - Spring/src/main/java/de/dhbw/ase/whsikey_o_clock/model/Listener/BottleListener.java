package de.dhbw.ase.whsikey_o_clock.model.Listener;

import de.dhbw.ase.whsikey_o_clock.model.Bottle;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class BottleListener {

    private static Log log = LogFactory.getLog(BottleListener.class);

    @PrePersist
    private void beforeAnyUpdate(Bottle bottle) {
        log.info("[BOTTLE AUDIT] About to add a bottle");
    }
}
