module CSVReader {
    exports shipment.csv; // Required for Integration Tests
    requires ShipmentAPI;

    provides shipment.api.ShipmentReader with shipment.csv.CSVReader;
}