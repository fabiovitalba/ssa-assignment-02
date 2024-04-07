module Main {
    exports shipment.main; // Required for Integration Tests

    requires ShipmentAPI;

    uses shipment.api.ShipmentReader;
    uses shipment.api.TaxesCalculator;
}