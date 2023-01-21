package pl.projekt.wsb.model;

import java.util.Objects;

public abstract class Vehicle {
    private String brand;
    private String color;
    private int price;
    private String regNumber;



    Vehicle(final String brand, final String color, final int price, final String regNumber) {
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.regNumber = regNumber;
    }
    public abstract String toCsv();


    @Override
    public String toString() {
        return
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price

                ;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vehicle vehicle = (Vehicle) o;
        return price == vehicle.price && Objects.equals(brand, vehicle.brand) && Objects.equals(color, vehicle.color) && Objects.equals(regNumber, vehicle.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, color, price, regNumber);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }
    String getRegNumber() {
        return regNumber;
    }

    void setRegNumber(final String regNumber) {
        this.regNumber = regNumber;
    }


}
