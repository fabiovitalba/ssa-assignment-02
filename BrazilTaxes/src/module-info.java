module BrazilTaxes {
    requires ShipmentAPI;

    provides shipment.api.TaxesCalculator with shipment.brazil.BrazilTaxes;
}