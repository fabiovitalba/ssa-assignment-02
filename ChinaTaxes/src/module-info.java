module ChinaTaxes {
    exports shipment.china; // Required for Unit Tests

    requires ShipmentAPI;
    requires junit;

    provides shipment.api.TaxesCalculator with shipment.china.ChinaTaxes;
}