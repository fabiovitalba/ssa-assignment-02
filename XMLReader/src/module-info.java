module XMLReader {
    requires ShipmentAPI;
    requires java.xml;

    provides shipment.api.ShipmentReader with shipment.xml.XMLReader;
}