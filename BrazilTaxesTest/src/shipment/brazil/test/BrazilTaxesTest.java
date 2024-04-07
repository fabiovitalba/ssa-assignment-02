package shipment.brazil.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.brazil.BrazilTaxes;

public class BrazilTaxesTest {
    @Test
    public void calculateFoodTaxTest() {
        Shipment s = new Shipment("apple","FOOD",20,1.0,00.0,"Brazil");
        BrazilTaxes bt = new BrazilTaxes();
        double calculatedTax = bt.calculateTax(s);
        assertEquals((20 * 0.3),calculatedTax,0.001);
    }
}
