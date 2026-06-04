package ar.edu.info.unlp.refactoring.ejercicio1;

import java.time.LocalDate;

public class Product {
    protected TimePeriod timePeriod;

    public Product(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public LocalDate startDate() {
        return this.timePeriod.start();
    }

    public LocalDate endDate() {
        return this.timePeriod.end();
    }
}
