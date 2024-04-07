package shipment.italy.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.italy.ItalyTaxes;

public class ItalyTaxesTest {
    @Test
    public void calculateFoodTaxTest() {
        Shipment s = new Shipment("apple","FOOD",20,1.0,00.0,"Italy");
        ItalyTaxes it = new ItalyTaxes();
        double calculatedTax = it.calculateTax(s);
        assertEquals((20 * 0.3),calculatedTax,0.001);
    }
}
