package shipment.main;

import shipment.api.ShipmentReader;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");
        // This retrieves implementations of the ShipmentReader interface.
        // Using the module-dependencies, we assign the ShipmentReader-implementations to our Main Module.
        // Once the module-dependencies are assigned, the ServiceLoader finds the implementations from the dependencies.
        Iterator<ShipmentReader> readers = ServiceLoader.load(ShipmentReader.class).iterator();
        while(readers.hasNext()) {
            // For each implementation we can print the FileType it handles.
            System.out.println(readers.next().getFileType());
        }
    }
}
