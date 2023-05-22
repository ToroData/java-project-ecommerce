package edu.uoc.pac4;
import edu.uoc.pac4.exception.ProductException;
public class PrintedBook extends Product implements Manufacturable {
    private static final String DESCRIPTION = "A book printed by an editorial";
    private double printingCost;

    public PrintedBook(String name, double price, double printingCost) throws ProductException {
        super(name, price);
        this.printingCost = printingCost;
    }

    public double getPrintingCost() {
        return printingCost;
    }

    public void setPrintingCost(double printingCost) {
        this.printingCost = printingCost;
    }

    @Override
    public String describeProduct() {
        return String.format("PrintedBook (MANUFACTURED): %s", DESCRIPTION);
    }

    @Override
    public double auditBenefits() {
        double benefits = (getPrice() - printingCost) * getSoldUnits() * 0.9;
        return benefits;
    }
}
