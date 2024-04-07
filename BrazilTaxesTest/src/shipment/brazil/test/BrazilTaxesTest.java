package shipment.brazil.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.brazil.BrazilTaxes;

public class BrazilTaxesTest {
    @Test
    public void calculateDefaultTaxTest() {
        int itemQuantity = 20;
        double unitPrice = 2.50;
        double shipmentCost = 100.0;
        double expectedTax = 40.0;

        Shipment s = new Shipment("trousers","CLOTHES",itemQuantity,unitPrice,shipmentCost,"Brazil");
        BrazilTaxes bt = new BrazilTaxes();
        double calculatedTaxAmount = bt.calculateTax(s);
        double expectedTaxAmount = ((itemQuantity * unitPrice) + shipmentCost) * (expectedTax / 100);
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateFoodTaxTest() {
        int itemQuantity = 1;
        double unitPrice = 24900;
        double shipmentCost = 100.0;
        double expectedTax = 40.0;

        Shipment s = new Shipment("apple","FOOD",itemQuantity,unitPrice,shipmentCost,"Brazil");
        BrazilTaxes bt = new BrazilTaxes();
        double calculatedTaxAmount = bt.calculateTax(s);
        double expectedTaxAmount = ((itemQuantity * unitPrice) + shipmentCost) * (expectedTax / 100);
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateFoodHighAmountTaxTest() {
        int itemQuantity = 1;
        // An Amount of 25000 * 0.4 results in exactly 10.000 of taxes.
        double unitPrice = 25000;
        double shipmentCost = 100.0;
        double expectedTax = 40.0;

        Shipment s = new Shipment("apple","FOOD",itemQuantity,unitPrice,shipmentCost,"Brazil");
        BrazilTaxes bt = new BrazilTaxes();
        double calculatedTaxAmount = bt.calculateTax(s);
        double expectedTaxAmount = ((itemQuantity * unitPrice) + shipmentCost) * (expectedTax / 100);
        double discountedTaxAmount = (expectedTaxAmount - 10000) * 0.6;
        assertEquals(10000 + discountedTaxAmount,calculatedTaxAmount,0.001);
    }
}
