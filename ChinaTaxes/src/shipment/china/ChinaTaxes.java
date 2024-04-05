package shipment.china;

import shipment.api.Country;
import shipment.api.Shipment;
import shipment.api.TaxesCalculator;

@Country(country = "China")
public class ChinaTaxes implements TaxesCalculator {
    @Override
    public double calculateTax(Shipment s) {
        double taxation;
        double totalAmount = s.getIndividualPrice() * s.getAmount();
        if (s.getProductType().equalsIgnoreCase("ELECTRONICS")) {
            taxation = 0.5;
        } else {
            taxation = 0.3;
        }

        // "For more than 1000 units or a total price of 100.000, a reduction of 20% of the total should be applied."
        // I assume that this means: MORE THAN 1.000 Units OR MORE THAN 100.000 of Total Amount
        if ((totalAmount > 100000) || (s.getAmount() > 1000))
            return totalAmount * taxation * 0.8;
        else
            return totalAmount * taxation;
    }
}
