module XMLReader {
    exports shipment.xml; // Required for Integration Tests

    requires ShipmentAPI;
    requires java.xml;

    provides shipment.api.ShipmentReader with shipment.xml.XMLReader;
}