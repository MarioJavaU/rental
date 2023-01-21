package pl.projekt.wsb.model;

import pl.projekt.wsb.exception.ClientAlreadyExistsException;
import pl.projekt.wsb.exception.VehicleAlreadyExistsException;

import java.util.*;

public class Rental {

    private Map<String, Vehicle> vehicles = new HashMap<>();

    private Map<String, RentalClient> clients = new HashMap<>();


    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }
    public Map<String, RentalClient> getClient() {
        return clients;
    }

    //dodajemy użytkowników, przyjmujemy pesel jako klucz aby nie było duplikatów.
    public void addClient(RentalClient client) {
        if (clients.containsKey(client.getPesel())) {
            throw new ClientAlreadyExistsException(
                    "Podany klient występuje w bazie aplikacji, PESEL: " + client.getPesel());
        }
        clients.put(client.getPesel(), client);
    }

    Map<String, RentalClient> getClients() {
        return clients;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicles.containsKey(vehicle.getRegNumber()))
            throw new VehicleAlreadyExistsException("Pojazd o podanym numerze rejestracyjnym istnieje w bazie aplikacji");
        vehicles.put(vehicle.getRegNumber(), vehicle);
    }

    public Collection<Vehicle> getSortedVehicle(Comparator<Vehicle> comparator){
        ArrayList<Vehicle> list = new ArrayList<>(this.vehicles.values());
        list.sort(comparator);
        return list;
    }
    public Collection<RentalClient> getSortedUsers(Comparator<RentalClient> comparator) {
        ArrayList<RentalClient> list = new ArrayList<>(this.clients.values());
        list.sort(comparator);
        return list;
    }




}



