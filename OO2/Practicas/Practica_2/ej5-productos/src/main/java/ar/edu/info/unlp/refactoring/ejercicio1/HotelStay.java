package ar.edu.info.unlp.refactoring.ejercicio1;

import java.time.LocalDate;

public class HotelStay extends Product {
    private double quote;
    private Hotel hotel;

    public HotelStay(double quote, TimePeriod timePeriod, Hotel hotel) {
        super(timePeriod);
        this.quote = quote;
        this.hotel = hotel;
    }

    public double getQuote() {
        return this.quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public double priceFactor() {
        return this.quote / this.price();
    }

    public double price() {
        return this.hotel.getPriceForDuration(this.timePeriod.duration());
    }
}
