package pl.projekt.wsb.file;

import pl.projekt.wsb.model.Rental;

public interface FileManager {
    Rental importData();
    void exportData(Rental rental);
}
