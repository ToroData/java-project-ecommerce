package edu.uoc.pac4;

public interface Billable {
    double TAX = 0.21;

    String bill();

    default double taxValue(double totalPrice) {
        return totalPrice - totalPrice / (1 + TAX);
    }
}
