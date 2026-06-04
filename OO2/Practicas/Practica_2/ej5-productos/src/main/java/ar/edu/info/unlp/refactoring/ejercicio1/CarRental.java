package ar.edu.info.unlp.refactoring.ejercicio1;

import java.time.LocalDate;

public class CarRental extends Product {
    private double quote;
    private Company company;

    public CarRental(double quote, TimePeriod timePeriod, Company company) {
        super(timePeriod);
        this.quote = quote;
        this.company = company;
    }

    public double getQuote() {
        return this.quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public double price() {
        return this.company.getPrice();
    }

    public double cost() {
        return this.quote;
    }
}
