package shipment.csv;

import shipment.api.FileType;
import shipment.api.Shipment;
import shipment.api.ShipmentReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@FileType(fileExtension = "csv")
public class CSVReader implements ShipmentReader {
    @Override
    public List<Shipment> readFile(String filepath) {
        List<Shipment> shipments = new ArrayList<>();

        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String csvFileLine = myReader.nextLine();
                Shipment shipment = newShipmentFromCsvLine(csvFileLine);
                if (shipment != null)
                    shipments.add(shipment);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // We just throw a RuntimeException to avoid having to deal with the FileNotFoundException directly
            // A better way of dealing with this could be to add the Exception to the Interface,
            // but since this is an Assignment we decided to simply throw an exception.
            throw new RuntimeException(e);
        }

        return shipments;
    }

    private Shipment newShipmentFromCsvLine(String csvFileLine) {
        String[] fileLineFields = csvFileLine.split(";");
        if (fileLineFields.length == 6) {
            String productName = fileLineFields[0];
            String productType = fileLineFields[1];
            int amount = Integer.parseInt(fileLineFields[2]);
            double individualPrice = Double.parseDouble(fileLineFields[3]);
            double shipmentPrice = Double.parseDouble(fileLineFields[4]);
            String country = fileLineFields[5];
            return new Shipment(productName, productType, amount, individualPrice, shipmentPrice, country);
        }
        return null;
    }
}
