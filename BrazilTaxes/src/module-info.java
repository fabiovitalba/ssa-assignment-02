module BrazilTaxes {
    exports shipment.brazil; // Required for Unit Tests

    requires ShipmentAPI;

    provides shipment.api.TaxesCalculator with shipment.brazil.BrazilTaxes;
}