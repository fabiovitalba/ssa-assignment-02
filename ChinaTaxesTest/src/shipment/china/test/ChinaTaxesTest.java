package shipment.china.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.china.ChinaTaxes;

public class ChinaTaxesTest {
    @Test
    public void calculateDefaultTaxTest() {
        int itemQuantity = 1000;
        double unitPrice = 2.50;
        double expectedTax = 30.0;

        Shipment s = new Shipment("apple","FOOD",itemQuantity,unitPrice,100.0,"China");
        ChinaTaxes ct = new ChinaTaxes();
        double calculatedTaxAmount = ct.calculateTax(s);
        assertEquals((itemQuantity * unitPrice) * (expectedTax / 100),calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateElectronicTaxTest() {
        int itemQuantity = 1000;
        double unitPrice = 2.50;
        double expectedTax = 50.0;

        Shipment s = new Shipment("microprocessor","ELECTRONICS",itemQuantity,unitPrice,100.0,"China");
        ChinaTaxes ct = new ChinaTaxes();
        double calculatedTaxAmount = ct.calculateTax(s);
        assertEquals((itemQuantity * unitPrice) * (expectedTax / 100),calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateBulkQuantityTaxTest() {
        int itemQuantity = 1001;
        double unitPrice = 2.50;
        double expectedTax = 30.0;

        Shipment s = new Shipment("apple","FOOD",itemQuantity,unitPrice,100.0,"China");
        ChinaTaxes ct = new ChinaTaxes();
        double calculatedTaxAmount = ct.calculateTax(s);
        double expectedTaxAmount = ((itemQuantity * unitPrice) * (expectedTax / 100)) * 0.8;
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateHighAmountTaxTest() {
        int itemQuantity = 1;
        double unitPrice = 100000.1;
        double expectedTax = 50.0;

        Shipment s = new Shipment("microprocessor","ELECTRONICS",itemQuantity,unitPrice,100.0,"China");
        ChinaTaxes ct = new ChinaTaxes();
        double calculatedTaxAmount = ct.calculateTax(s);
        double expectedTaxAmount = ((itemQuantity * unitPrice) * (expectedTax / 100)) * 0.8;
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }
}
