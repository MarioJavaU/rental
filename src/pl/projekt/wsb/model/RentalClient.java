package pl.projekt.wsb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentalClient extends Client {
    private List<Vehicle> rentHistory = new ArrayList<>();
    private List<Vehicle> rentActive = new ArrayList<>();

    public RentalClient(final String firstName, final String lastName, final String pesel) {
        super(firstName, lastName, pesel);

    }

    private void addRentToHistory(Vehicle v){
        rentHistory.add(v);
    }

    public void rentActive(Vehicle v){
        rentActive.add(v);
    }

    public boolean returnVehicle(Vehicle v){
        boolean result = false;
        if (rentActive.remove(v)){
            result = true;
            addRentToHistory(v);
        }
        return result;
    }

    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getPesel();
    }

    public List<Vehicle> getRentHistory() {
        return rentHistory;
    }

    public List<Vehicle> getRentActive() {
        return rentActive;
    }




    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        final RentalClient that = (RentalClient) o;
        return Objects.equals(rentHistory, that.rentHistory) && Objects.equals(rentActive, that.rentActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rentHistory, rentActive);
    }
}
