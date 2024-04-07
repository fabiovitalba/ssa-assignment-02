package shipment.main;

import shipment.api.*;

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
            if (!sr.getClass().isAnnotationPresent(FileType.class))
                throw new RuntimeException("Class " + sr.getClass() + " needs a " + FileType.class + " annotation.");
            readers.put(sr.getClass().getAnnotation(FileType.class).fileExtension(),sr);
        }

        Iterator<TaxesCalculator> taxesCalculatorIterator = ServiceLoader.load(TaxesCalculator.class).iterator();
        while(taxesCalculatorIterator.hasNext()) {
            TaxesCalculator tc = taxesCalculatorIterator.next();
            if (!tc.getClass().isAnnotationPresent(Country.class))
                throw new RuntimeException("Class " + tc.getClass() + " needs a " + Country.class + " annotation.");
            calcs.put(tc.getClass().getAnnotation(Country.class).country(),tc);
        }
    }

    public static double calculate(String fileName) {
        String fileExtension = returnFileExtension(fileName);
        if (!readers.containsKey(fileExtension))
            throw new RuntimeException("No shipment reader for " + fileExtension + " files.");
        ShipmentReader sr = readers.get(fileExtension);
        List<Shipment> shipments = sr.readFile(fileName);
        return shipments.stream()
                .mapToDouble(shipment -> {
                    if (calcs.containsKey(shipment.getCountry())) {
                        TaxesCalculator tc = calcs.get(shipment.getCountry());
                        return tc.calculateTax(shipment);
                    } else {
                        System.out.println("No tax calculator for " + shipment.getCountry() + ".");
                        return 0;
                    }
                })
                .sum();
    }

    private static String returnFileExtension(String fileName) {
        String[] fileTokens = fileName.split("\\.");
        return fileTokens[fileTokens.length - 1];
    }
}
