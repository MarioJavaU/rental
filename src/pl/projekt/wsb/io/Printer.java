package pl.projekt.wsb.io;

import pl.projekt.wsb.model.Car;
import pl.projekt.wsb.model.Motorbike;
import pl.projekt.wsb.model.RentalClient;
import pl.projekt.wsb.model.Vehicle;

import java.util.Collection;

public class Printer {
    public void printCars(Collection<Vehicle>vehicles){
        int counter = 0;
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Car){
                printLine(vehicle.toString());
                counter++;
            }
        }
        if(counter == 0){
            printLine("Brak samochodów w wypożyczalni");
        }
    }

    public void printMotorbikes(Collection<Vehicle>vehicles){
        int counter = 0;
        for (Vehicle vehicle : vehicles){
            if (vehicle instanceof Motorbike){
                printLine(vehicle.toString());
                counter++;
            }
        }
        if(counter == 0){
            printLine("Brak Motocykli w wypożyczalni");
        }
    }
    public void printClients(Collection<RentalClient> clients) {
        for (RentalClient client : clients) {
            printLine(client.toString());
        }
    }




    public void printLine(String text) {
        System.out.println(text);
    }
}
