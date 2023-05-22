package edu.uoc.pac4;

import java.util.Objects;

import edu.uoc.pac4.exception.ProductException;

public abstract class Product {
    private String name;
    private double price;
    private int soldUnits;

    public Product(String name, double price) throws ProductException {
        setName(name);
        setPrice(price);
        this.soldUnits = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ProductException {
        if (name == null || name.isEmpty()) {
            throw new ProductException(ProductException.ERR_EMPTY_NAME);
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws ProductException {
        if (price <= 0) {
            throw new ProductException(ProductException.ERR_INVALID_PRICE);
        }
        this.price = price;
    }

    public int getSoldUnits() {
        return soldUnits;
    }

    public void addSoldUnits(int quantity) {
        soldUnits += quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return Objects.equals(name, other.name);
    }

    public abstract String describeProduct();
}
