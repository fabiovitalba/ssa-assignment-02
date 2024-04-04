module Main {
    requires ShipmentAPI;

    uses shipment.api.ShipmentReader;
    uses shipment.api.TaxesCalculator;
}