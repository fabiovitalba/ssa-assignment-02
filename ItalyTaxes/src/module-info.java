module ItalyTaxes {
    requires ShipmentAPI;

    provides shipment.api.TaxesCalculator with shipment.italy.ItalyTaxes;
}