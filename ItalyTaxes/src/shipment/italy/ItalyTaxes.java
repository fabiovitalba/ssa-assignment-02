package shipment.italy;

import shipment.api.Shipment;
import shipment.api.TaxesCalculator;

public class ItalyTaxes implements TaxesCalculator {
    @Override
    public double calculateTax(Shipment s) {
        double taxation = 0.3;
        double totalAmount = s.getIndividualPrice() * s.getAmount();
        double totalTaxAmount = totalAmount * taxation;
        if (s.getIndividualPrice() > 500) {
            totalTaxAmount += 5.0;
        }

        if (s.getProductType().equalsIgnoreCase("CLOTHES") && (totalAmount < 50000)) {
            totalTaxAmount = totalTaxAmount * 0.8;
        }

        return  totalTaxAmount;
    }

    @Override
    public String getCountry() {
        return "Italy";
    }
}
