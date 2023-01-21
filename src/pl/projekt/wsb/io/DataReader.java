package pl.projekt.wsb.io;

import pl.projekt.wsb.model.Car;
import pl.projekt.wsb.model.Motorbike;
import pl.projekt.wsb.model.RentalClient;
import pl.projekt.wsb.model.Vehicle;

import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private Printer printer;

    public DataReader(Printer printer){
        this.printer = printer;
    }

    public void close(){
        scanner.close();
    }

    public int getInt(){
       try{
           return scanner.nextInt();
       }finally {
           scanner.nextLine();
       }

    }
    public String getString(){
        return scanner.nextLine();
    }

    public Car readAndCreateCar(){
        printer.printLine("Marka: ");
        String brand = scanner.nextLine();
        printer.printLine("Kolor: ");
        String color = scanner.nextLine();;
        printer.printLine("cena za dzien: ");
        int price = getInt();
        printer.printLine("Numer rejestracyjny: ");
        String regNum = scanner.nextLine();
        printer.printLine("skrzynia biegow: ");
        String gearbox = scanner.nextLine();;

        return new Car(brand,color,price, regNum, gearbox);
    }
    public Motorbike readAndCreateMotorBike(){
        printer.printLine("Marka: ");
        String brand = scanner.nextLine();
        printer.printLine("Kolor: ");
        String color = scanner.nextLine();;
        printer.printLine("cena za dzien: ");
        int price = getInt();
        printer.printLine("Numer rejestracyjny: ");
        String regNum = scanner.nextLine();
        printer.printLine("Typ motocykla");
        String type = scanner.nextLine();

        return new Motorbike(brand,color,price, regNum, type);
    }
    public RentalClient createRentalClient() {
        printer.printLine("Imię");
        String firstName = scanner.nextLine();
        printer.printLine("Nazwisko");
        String lastName = scanner.nextLine();
        printer.printLine("Pesel");
        String pesel = scanner.nextLine();
        return new RentalClient(firstName, lastName, pesel);
    }

}
