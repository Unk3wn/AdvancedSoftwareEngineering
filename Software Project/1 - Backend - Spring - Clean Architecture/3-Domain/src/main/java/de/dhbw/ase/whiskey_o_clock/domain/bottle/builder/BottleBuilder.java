package de.dhbw.ase.whiskey_o_clock.domain.bottle.builder;

import de.dhbw.ase.whiskey_o_clock.domain.bottle.Bottle;
import de.dhbw.ase.whiskey_o_clock.domain.manufacturer.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.domain.series.Series;

import java.util.UUID;

public class BottleBuilder {

    final String label;
    UUID uuid;
    double price;
    int yearOfManufacture;
    Manufacturer manufacturer;
    boolean forSale;
    boolean favorite;
    boolean unsaleable;
    Series series;

    public BottleBuilder(String label) {
        this.label = label;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BottleBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public BottleBuilder price(double price) {
        this.price = price;
        return this;
    }

    public BottleBuilder yearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
        return this;
    }

    public BottleBuilder manufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public BottleBuilder forSale(boolean forSale) {
        this.forSale = forSale;
        return this;
    }

    public BottleBuilder favorite(boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    public BottleBuilder unsaleable(boolean unsaleable) {
        this.unsaleable = unsaleable;
        return this;
    }

    public BottleBuilder series(Series series) {
        this.series = series;
        return this;
    }

    public Bottle build() {
        Bottle bottle = new Bottle(this);
        //Validation
        return bottle;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public boolean isForSale() {
        return forSale;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isUnsaleable() {
        return unsaleable;
    }

    public Series getSeries() {
        return series;
    }
}
