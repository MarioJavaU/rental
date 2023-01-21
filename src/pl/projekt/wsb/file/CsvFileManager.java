package pl.projekt.wsb.file;


import pl.projekt.wsb.exception.DataExportException;
import pl.projekt.wsb.exception.DataImportException;
import pl.projekt.wsb.exception.InvalidDataException;
import pl.projekt.wsb.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Rental.csv";
    private static final String FILE_NAME_CLIENTS= "RentalClients.csv";


    //pobieramy wszystkie pojazdy za pomocą pętli, zamieniamy pobrane elementy na tekst rozdzielony średnikami
    //dzięki metodzie toCsv(). W przypadku będu zapisu rzucamy wyjątek.
    @Override
    public void exportData(Rental rental) {
        exportVehicle(rental);
        exportRentalClients(rental);

    }

    @Override
    public Rental importData() {
        Rental rental = new Rental();
        importClients(rental);
        importVehicles(rental);
        return rental;
    }

    private void exportRentalClients(final Rental rental) {
        Collection<RentalClient> clients = rental.getClient().values();
        try(FileWriter fileWriter = new FileWriter(FILE_NAME_CLIENTS);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (RentalClient client : clients){
                bufferedWriter.write(client.toCsv());
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            throw new DataExportException("Blad zapisu danych do pliku" + FILE_NAME_CLIENTS);
        }
    }

    private void exportVehicle(final Rental rental) {
        Collection<Vehicle> vehicles = rental.getVehicles().values();
        try(FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Vehicle vehicle : vehicles){
                bufferedWriter.write(vehicle.toCsv());
                bufferedWriter.newLine();
            }
        }catch (IOException e){
            throw new DataExportException("Blad zapisu danych do pliku" + FILE_NAME);
        }
    }

    //metoda wczytuje dane z plików i odtwarza z nich obiekty



    private void importVehicles(Rental rental) {
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Vehicle vehicle = createObjectFromString(line);
                rental.addVehicle(vehicle);
            }
        }catch (FileNotFoundException e){
            throw new DataImportException("Brak pliku " + FILE_NAME);
        }
    }
    private void importClients(Rental rental) {
        try (Scanner fileReader = new Scanner(new File(FILE_NAME_CLIENTS))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                RentalClient client = createClientFromString(line);
                rental.addClient(client);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME_CLIENTS);
        }
    }

    //metoda rozdziela wiersze tekstu na tablice napisów z separaotrem,
    //pierwszy wyraz określa czy jest to motocykl czy samochód i za pomocą
    //instrukcji if dobiera odpowiednia metodę
    private Vehicle createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if(Car.TYPE.equals(type)){
            return createCar(split);
        } else if (Motorbike.TYPE.equals(type)) {
            return createMotorbike(split);
        }
        throw new InvalidDataException("Nieznany typ pojazdu" + type);
    }
    private RentalClient createClientFromString(String csvText) {
        String[] split = csvText.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new RentalClient(firstName, lastName, pesel);
    }

    private Motorbike createMotorbike(String[] data) {
        String brand = data[1];
        String color = data[2];
        int price = Integer.valueOf(data[3]);
        String regNumber = data[4];
        String type = data[5];
        return new Motorbike(brand,color,price,regNumber,type);
    }

    private Car createCar(String[] data) {
        String brand = data[1];
        String color = data[2];
        int price = Integer.valueOf(data[3]);
        String regNumber = data[4];
        String gearbox = data[5];
        return new Car(brand,color,price,regNumber,gearbox);
    }
}
