package de.dhbw.ase.whiskey_o_clock.domain.series.listener;

import de.dhbw.ase.whiskey_o_clock.domain.series.Series;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.*;

public class SeriesListener {

    private static Log log = LogFactory.getLog(SeriesListener.class);

    @PrePersist
    public void logNewAttempt(Series series) {
        log.info(String.format("[SERIES AUDIT] Attempting to add a new Series with Label '%s'",series.getLabel()));
    }

    @PostPersist
    public void logNewAdded(Series series) {
        log.info(String.format("[SERIES AUDIT] Added Series ['%s'] with Label '%s'",series.getUuid(),series.getLabel()));
    }

    @PreRemove
    public void logRemovalAttempt(Series series) {
        log.info(String.format("[SERIES AUDIT] Attempting to remove Series ['%s'] with Label '%s'",series.getUuid(),series.getLabel()));
    }

    @PostRemove
    public void logRemoval(Series series) {
        log.info(String.format("[SERIES AUDIT] Removed Series with Label '%s'",series.getLabel()));
    }

    @PreUpdate
    public void logUpdateAttempt(Series series) {
        log.info(String.format("[SERIES AUDIT] Attempting to update Series ['%s'] with Label '%s'",series.getUuid(),series.getLabel()));
    }

    @PostUpdate
    public void logUpdate(Series series) {
        log.info(String.format("[SERIES AUDIT] Updated Series ['%s'] with Label '%s'",series.getUuid(),series.getLabel()));
    }

    @PostLoad
    public void logLoad(Series series) {
        log.info(String.format("[SERIES AUDIT] Loading Series ['%s'] with Label '%s'",series.getUuid(),series.getLabel()));
    }

}
