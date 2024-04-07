package shipment.main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");

        double taxOwedCsv = ShipmentFileProcessor.calculate("Main/shipments.csv");
        System.out.println("Tax owed CSV: " + taxOwedCsv);

        double taxOwedXml = ShipmentFileProcessor.calculate("Main/shipments.xml");
        System.out.println("Tax owed XML: " + taxOwedXml);
    }
}
