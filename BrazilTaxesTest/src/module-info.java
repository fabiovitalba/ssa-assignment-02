module BrazilTaxesTest {
    exports shipment.brazil.test;    // Export to JUnit for testing

    requires junit;
    requires BrazilTaxes;
    requires ShipmentAPI;
}