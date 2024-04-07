module ItalyTaxes {
    exports shipment.italy; // Required for Unit Tests

    requires ShipmentAPI;

    provides shipment.api.TaxesCalculator with shipment.italy.ItalyTaxes;
}