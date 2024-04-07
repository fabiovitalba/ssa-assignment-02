package shipment.china.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import shipment.api.Shipment;
import shipment.china.ChinaTaxes;

public class ChinaTaxesTest {
    @Test
    public void calculateFoodTaxTest() {
        Shipment s = new Shipment("apple","FOOD",20,1.0,00.0,"China");
        ChinaTaxes ct = new ChinaTaxes();
        double calculatedTax = ct.calculateTax(s);
        assertEquals((20 * 0.3),calculatedTax,0.001);
    }
}
