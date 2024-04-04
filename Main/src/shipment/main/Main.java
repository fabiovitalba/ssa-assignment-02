package shipment.main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");

        double taxOwedCsv = ShipmentFileProcessor.calculate("/Users/fabiovitalba/Development/Java/ssa-assignment-02/example/shipments.csv");
        System.out.println("Tax owed CSV: " + taxOwedCsv);
        
        double taxOwedXml = ShipmentFileProcessor.calculate("/Users/fabiovitalba/Development/Java/ssa-assignment-02/example/shipments.xml");
        System.out.println("Tax owed XML: " + taxOwedXml);
    }
}
