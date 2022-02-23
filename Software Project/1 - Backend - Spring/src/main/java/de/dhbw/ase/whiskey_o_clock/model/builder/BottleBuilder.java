package de.dhbw.ase.whiskey_o_clock.model.builder;

import de.dhbw.ase.whiskey_o_clock.model.Bottle;
import de.dhbw.ase.whiskey_o_clock.model.Manufacturer;
import de.dhbw.ase.whiskey_o_clock.model.Series;

public class BottleBuilder {

    final String label;
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
        //Validation
        return new Bottle(this);
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
