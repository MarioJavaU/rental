package pl.projekt.wsb.app;

import pl.projekt.wsb.exception.ClientAlreadyExistsException;
import pl.projekt.wsb.exception.DataExportException;
import pl.projekt.wsb.exception.DataImportException;
import pl.projekt.wsb.exception.NoSuchOptionException;
import pl.projekt.wsb.file.CsvFileManager;
import pl.projekt.wsb.file.FileManager;
import pl.projekt.wsb.exception.InvalidDataException;
import pl.projekt.wsb.io.DataReader;
import pl.projekt.wsb.io.Printer;
import pl.projekt.wsb.model.*;
import java.util.InputMismatchException;

public class RentalControl {
    //  zmienna do komunikacji z użytkownikiem z tworzeniem printera poprzez konstruktor
    private Printer printer = new Printer();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    //zmienna przechowująca dane
    private Rental rental;

    RentalControl() {
        fileManager = new CsvFileManager();
        try {
            rental = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Utworzono nową bazę");
            rental = new Rental();
        }
    }

    /*pętla pełniąca role menu do wyboru opcji
      pętla do while z uwagi na to że musi wykonać się conajmniej raz
       aby wyświetlić dostępne opcje
     */
    public void controlLoop() throws NoSuchOptionException {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_CAR:
                    addCar();
                    break;
                case ADD_MOTORBIKE:
                    addMotorbike();
                    break;
                case PRINT_CAR:
                    printCars();
                    break;
                case PRINT_MOTORBIKE:
                    printMotorbike();
                    break;
                case ADD_CLIENT:
                    addClient();
                    break;
                case PRINT_CLIENTS:
                    printClients();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji, wprowadź numer ponownie: ");
            }
        } while (option != Option.EXIT);
    }

    private void addClient() {
        RentalClient rentalClient = dataReader.createRentalClient();
        try {
            rental.addClient(rentalClient);
        }catch (ClientAlreadyExistsException e){
            printer.printLine(e.getMessage());
        }
    }

    // metoda pozwalająca pobrać opcje od uzytkownika do momentu podania prawidlowej wartosci
    private Option getOption() {
        boolean ok = false;
        Option option = null;
        while (!ok) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                ok = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + "Podaj ponownie numer opcji");
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadziłeś wartośc nieliczbową, podaj ponownie");
            }
        }
        return option;
    }





    private void printOptions() {
        System.out.println("Wybierz Opcje: ");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private void addCar() {

        try {
            Car car = dataReader.readAndCreateCar();
            rental.addVehicle(car);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyc samochodu, niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnieto limit pojemnosci, nie można dodac kolejnego samochodu");
        }
    }
    private void addMotorbike() {
        try {
            Motorbike motorbike = dataReader.readAndCreateMotorBike();
            rental.addVehicle(motorbike);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udalo się utworzyc motoru, niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnieto limit pojemnosci, nie można dodać kolejnego motoru");
        }
    }
    private void printCars() {
       printer.printCars(rental.getSortedVehicle(
               (v1,v2)-> v1.getBrand().compareToIgnoreCase(v2.getBrand())
       ));
    }
    private void printMotorbike() {
        printer.printMotorbikes(rental.getSortedVehicle(
                (v1,v2)-> v1.getBrand().compareToIgnoreCase(v2.getBrand())
        ));
    }
    private void printClients(){
        printer.printClients(rental.getSortedUsers(
                (p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName())
        ));
    }

    private void exit() {
        //zapis danych do pliku
        try {
            fileManager.exportData(rental);
            printer.printLine("Dane aplikacji zapisane!");
        }catch (DataExportException e){
            printer.printLine(e.getMessage());
        }

        // zamykamy strumień wejścia
        dataReader.close();
        printer.printLine("Koniec programu");

    }



    // klasa wewnetrzna enum do wyboru opcji
    private enum Option {
        EXIT(0, "Wyjscie z programu"),
        ADD_CAR(1, "Dodanie samochodu"),
        ADD_MOTORBIKE(2, "Dodanie motocykla"),
        PRINT_CAR(3, "Wyswietlenie dostepnych samochodow"),
        PRINT_MOTORBIKE(4, "Wyswietlenie dostepnych motocykli"),
        ADD_CLIENT(5,"Dodaj klienta"),
        PRINT_CLIENTS (6,"Wyswietl klientow");

        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }



        @Override
        public String toString() {
            return value + " - " + description;
        }

        // metoda przekształca wartość typu int na wartość typu Option
        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o numerze " + option);
            }
        }
    }
}


