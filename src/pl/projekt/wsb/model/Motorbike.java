package pl.projekt.wsb.model;

import java.util.Objects;

public class Motorbike extends Vehicle {
    public static final String TYPE = "MOTORBIKE";
    private String type;


    public Motorbike(final String brand, final String color, final int price, final String regNumber, String type) {
        super(brand, color, price, regNumber);
        this.type = type;
    }


    @Override
    public String toString() {
        return "Marka: " + getBrand() + "| kolor: " + getColor() +
                "| typ: " + type + "| cena za dzien: " + getPrice();
    }

    @Override
    public String toCsv() {
        return TYPE + ";" + getBrand() + ";" +
                getColor() + ";" + getPrice() +
                ";" + getRegNumber() + ";" + type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Motorbike motorbike = (Motorbike) o;
        return Objects.equals(type, motorbike.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
