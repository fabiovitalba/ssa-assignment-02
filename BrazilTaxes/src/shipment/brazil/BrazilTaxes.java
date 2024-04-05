package shipment.brazil;

import shipment.api.Country;
import shipment.api.Shipment;
import shipment.api.TaxesCalculator;

@Country(country = "Brazil")
public class BrazilTaxes implements TaxesCalculator {

    @Override
    public double calculateTax(Shipment s) {
        double taxation = 0.4;
        double totalAmount = s.getIndividualPrice() * s.getAmount() + s.getShipmentPrice();
        double totalTaxAmount = totalAmount * taxation;
        if (s.getProductType().equalsIgnoreCase("FOOD") && (totalTaxAmount > 10000)) {
            double exceedingAmount = totalTaxAmount - 10000;
            return 10000 + (exceedingAmount * 0.6);
        } else {
            return totalTaxAmount;
        }
    }
}
