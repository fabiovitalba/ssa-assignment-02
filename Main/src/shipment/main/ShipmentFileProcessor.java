package shipment.main;

import shipment.api.Shipment;
import shipment.api.ShipmentReader;
import shipment.api.TaxesCalculator;

import java.util.*;

public class ShipmentFileProcessor {
    private static Map<String, TaxesCalculator> calcs = new HashMap<>();
    private static Map<String, ShipmentReader> readers = new HashMap<>();

    // "A static block in Java is executed when the class is loaded."
    static {
        // We simply look for all implementations of our Interfaces

        // This retrieves implementations of the ShipmentReader interface.
        // Using the module-dependencies, we assign the ShipmentReader-implementations to our Main Module.
        // Once the module-dependencies are assigned, the ServiceLoader finds the implementations from the dependencies.
        Iterator<ShipmentReader> shipmentReaderIterator = ServiceLoader.load(ShipmentReader.class).iterator();
        while(shipmentReaderIterator.hasNext()) {
            ShipmentReader sr = shipmentReaderIterator.next();
            readers.put(sr.getFileType(),sr);
        }

        Iterator<TaxesCalculator> taxesCalculatorIterator = ServiceLoader.load(TaxesCalculator.class).iterator();
        while(taxesCalculatorIterator.hasNext()) {
            TaxesCalculator tc = taxesCalculatorIterator.next();
            calcs.put(tc.getCountry(),tc);
        }
    }

    public static double calculate(String fileName) {
        String fileExtension = returnFileExtension(fileName);
        System.out.println(fileExtension);
        ShipmentReader sr = readers.get(fileExtension);
        List<Shipment> shipments = sr.readFile(fileName);
        return shipments.stream()
                .mapToDouble(shipment -> {
                    TaxesCalculator tc = calcs.get(shipment.getCountry());
                    return tc.calculateTax(shipment);
                })
                .sum();
    }

    private static String returnFileExtension(String fileName) {
        String[] fileTokens = fileName.split("\\.");
        return fileTokens[fileTokens.length - 1];
    }
}
