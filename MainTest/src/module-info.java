module MainTest {
    exports shipment.main.test;    // Export to JUnit for testing

    requires junit;
    requires Main;
    requires ShipmentAPI;
    requires CSVReader;
    requires XMLReader;
    requires BrazilTaxes;
    requires ChinaTaxes;
    requires ItalyTaxes;
}