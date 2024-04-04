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
        String fileExtension = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
        // Alternative used in lab:
        // String fileExtension = fileName.substring(fileName.indexOf("."),fileName.length() - 1)
        ShipmentReader sr = readers.get(fileExtension);
        List<Shipment> shipments = sr.readFile(fileName);
        return shipments.stream()
                .mapToDouble(shipment -> {
                    TaxesCalculator tc = calcs.get(shipment.getCountry());
                    return tc.calculateTax(shipment);
                })
                .sum();
    }
}
