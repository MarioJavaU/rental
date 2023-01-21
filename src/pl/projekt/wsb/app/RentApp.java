package pl.projekt.wsb.app;

import pl.projekt.wsb.exception.NoSuchOptionException;

class RentApp {
    private static final String APP_NAME = "Wypozyczalnia v1.0";
    public static void main(String[] args) throws NoSuchOptionException {
        System.out.println(APP_NAME);
        RentalControl rentControl = new RentalControl();
        rentControl.controlLoop();


    }
}
