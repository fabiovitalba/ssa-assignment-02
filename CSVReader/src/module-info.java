module CSVReader {
    requires ShipmentAPI;

    provides shipment.api.ShipmentReader with shipment.csv.CSVReader;
}