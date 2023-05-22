package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderItemException;

import java.util.Objects;

public class OrderItem implements Billable {
    private Product product;
    private Order order;
    private int quantity;

    public OrderItem(Order order, Product product, int quantity) throws OrderItemException {
        setProduct(product);
        setOrder(order);
        setQuantity(quantity);
    }

    private void setProduct(Product product) throws OrderItemException {
        if (product == null) {
            throw new OrderItemException(OrderItemException.ERR_NULL_PRODUCT);
        }
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    private void setOrder(Order order) throws OrderItemException {
        if (order == null) {
            throw new OrderItemException(OrderItemException.ERR_NULL_ORDER);
        }
        this.order = order;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            quantity = 1;
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String bill() {
        double totalTax = taxValue(getTotalPrice());
        return String.format("Product: %s | Quantity: %d | Price: %.1f | Tax: %.2f",
                product.getName(), quantity, getTotalPrice(), totalTax).replace(',', '.');
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        return Objects.equals(product, other.product) &&
                Objects.equals(order, other.order);
    }
}
