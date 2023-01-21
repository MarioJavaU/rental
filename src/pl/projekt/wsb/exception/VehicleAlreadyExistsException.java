package pl.projekt.wsb.exception;

public class VehicleAlreadyExistsException extends RuntimeException{
    public VehicleAlreadyExistsException(final String message) {
        super(message);
    }
}
