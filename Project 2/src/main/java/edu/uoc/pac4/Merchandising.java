package edu.uoc.pac4;
import edu.uoc.pac4.exception.ProductException;
public class Merchandising extends Product implements Manufacturable {
    private static final String DESCRIPTION = "A merchandising item";
    private double fabricationCost;
    private double packagingCost;

    public Merchandising(String name, double price, double fabricationCost, double packagingCost) throws ProductException {
        super(name, price);
        this.fabricationCost = fabricationCost;
        this.packagingCost = packagingCost;
    }

    public double getFabricationCost() {
        return fabricationCost;
    }

    public void setFabricationCost(double fabricationCost) {
        this.fabricationCost = fabricationCost;
    }

    public double getPackagingCost() {
        return packagingCost;
    }

    public void setPackagingCost(double packagingCost) {
        this.packagingCost = packagingCost;
    }

    @Override
    public String describeProduct() {
        return String.format("Merchandising (MANUFACTURED): %s", DESCRIPTION);
    }

    @Override
    public double auditBenefits() {
        double benefits = (getPrice() - (fabricationCost + packagingCost)) * getSoldUnits();
        return benefits;
    }
}
