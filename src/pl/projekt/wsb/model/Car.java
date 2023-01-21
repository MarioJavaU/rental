package pl.projekt.wsb.model;

import java.util.Objects;

public class Car extends Vehicle{

    public static final String TYPE = "CAR";

    //dziedziczymy po Vehicle wszystkie pola i dodajemy cechę unikalną dla samochodów typ skrzyni biegów
    private String gearbox;


    public Car(final String brand, final String color, final int price, final String regNumber,final String gearbox) {
        super(brand, color, price, regNumber);
        this.gearbox = gearbox;
    }


    @Override
    public String toCsv() {
        return TYPE + ";" + getBrand() + ";" +
                getColor() + ";" + getPrice() + ";" +
                ";" + getRegNumber() + ";" + gearbox;
    }

    @Override
    public String toString() {
        return  "Marka: " + getBrand() + "| kolor: " + getColor() +
                "| skrzynia biegow: " + gearbox + "| cena za dzien: " +getPrice() ;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final Car car = (Car) o;
        return Objects.equals(gearbox, car.gearbox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gearbox);
    }

    public String getGearbox() {
        return gearbox;
    }


    public void setGearbox(final String gearbox) {
        this.gearbox = gearbox;
    }
}

