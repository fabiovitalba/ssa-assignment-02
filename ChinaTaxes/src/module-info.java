module ChinaTaxes {
    requires ShipmentAPI;

    provides shipment.api.TaxesCalculator with shipment.china.ChinaTaxes;
}