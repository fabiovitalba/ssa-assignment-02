package shipment.csv;

import shipment.api.Shipment;
import shipment.api.ShipmentReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader implements ShipmentReader {

    @Override
    public List<Shipment> readFile(String filepath) {
        List<Shipment> shipments = new ArrayList<>();

        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String csvFileLine = myReader.nextLine();
                Shipment shipment = newShipmentFromCsvLine(csvFileLine);
                if (shipment != null)
                    shipments.add(shipment);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return shipments;
    }

    @Override
    public String getFileType() {
        return "csv";
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
