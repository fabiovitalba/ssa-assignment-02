package shipment.italy.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.italy.ItalyTaxes;

public class ItalyTaxesTest {
    @Test
    public void calculateDefaultTaxTest() {
        int itemQuantity = 20;
        double unitPrice = 2.50;
        double shipmentCost = 100.0;
        double expectedTax = 30.0;

        Shipment s = new Shipment("pasta","FOOD",itemQuantity,unitPrice,shipmentCost,"Italy");
        ItalyTaxes it = new ItalyTaxes();
        double calculatedTaxAmount = it.calculateTax(s);
        double expectedTaxAmount = (itemQuantity * unitPrice) * (expectedTax / 100);
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateHighIndividualPriceTaxTest() {
        int itemQuantity = 20;
        double unitPrice = 2500;
        double shipmentCost = 100.0;
        double expectedTax = 30.0;

        Shipment s = new Shipment("wine","FOOD",itemQuantity,unitPrice,shipmentCost,"Italy");
        ItalyTaxes it = new ItalyTaxes();
        double calculatedTaxAmount = it.calculateTax(s);
        double expectedTaxAmount = (itemQuantity * unitPrice) * (expectedTax / 100) + 5;
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateClothesTaxTest() {
        int itemQuantity = 100;
        double unitPrice = 499;
        double shipmentCost = 100.0;
        double expectedTax = 30.0;

        Shipment s = new Shipment("dress","CLOTHES",itemQuantity,unitPrice,shipmentCost,"Italy");
        ItalyTaxes it = new ItalyTaxes();
        double calculatedTaxAmount = it.calculateTax(s);
        double expectedTaxAmount = (itemQuantity * unitPrice) * (expectedTax / 100) * 0.8;
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }

    @Test
    public void calculateClothesHighAmountTaxTest() {
        int itemQuantity = 100;
        double unitPrice = 501;
        double shipmentCost = 100.0;
        double expectedTax = 30.0;

        Shipment s = new Shipment("dress","CLOTHES",itemQuantity,unitPrice,shipmentCost,"Italy");
        ItalyTaxes it = new ItalyTaxes();
        double calculatedTaxAmount = it.calculateTax(s);
        double expectedTaxAmount = (itemQuantity * unitPrice) * (expectedTax / 100) + 5;
        assertEquals(expectedTaxAmount,calculatedTaxAmount,0.001);
    }
}
