package shipment.api;

public class Shipment {
    private String productName;
    private String productType;
    private int amount;
    private double individualPrice;
    private double shipmentPrice;
    private String country;

    public Shipment() {}

    public Shipment(String productName, String productType, int amount, double individualPrice, double shipmentPrice, String country) {
        this.productName = productName;
        this.productType = productType;
        this.amount = amount;
        this.individualPrice = individualPrice;
        this.shipmentPrice = shipmentPrice;
        this.country = country;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getIndividualPrice() {
        return individualPrice;
    }

    public void setIndividualPrice(double individualPrice) {
        this.individualPrice = individualPrice;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
