package edu.uoc.pac4;

import edu.uoc.pac4.exception.OrderException;
import edu.uoc.pac4.exception.OrderItemException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class Order implements Billable, Comparable<Order> {
    public static final int MAX_ORDER_ITEMS = 10;
    private final String id;
    private User user;
    private OrderItem[] orderItems;
    private LocalDate deliveryDate;
    private int itemCount;
    private LocalDate orderDate;

    public Order(User user, LocalDate orderDate) throws OrderException {
        if (user == null) {
            throw new OrderException(OrderException.ERR_NULL_USER);
        }
        this.id = generateOrderId();
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryDate = null;
        this.orderItems = new OrderItem[MAX_ORDER_ITEMS];
        this.itemCount = 0;
    }

    private String generateOrderId() {
        return UUID.randomUUID().toString();
    }


    private void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("The user cannot be null");
        }
        this.user = user;
    }


    public boolean addOrderItem(Product product, int quantity) throws OrderItemException {
        if (product == null) {
            throw new OrderItemException(OrderItemException.ERR_NULL_ORDER);
        }
        if (quantity <= 0) {
            throw new OrderItemException(OrderItemException.ERR_NULL_PRODUCT);
        }

        if (itemCount < MAX_ORDER_ITEMS) {
            orderItems[itemCount] = new OrderItem(this, product, quantity);
            itemCount++;
            return true;
        } else {
            return false;
        }
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) throws OrderException {
        if (deliveryDate != null && deliveryDate.isBefore(orderDate)) {
            throw new OrderException(OrderException.ERR_WRONG_DELIVERY_DATE);
        }
        this.deliveryDate = deliveryDate;
    }


    public boolean removeOrderItem(Product product, int quantity) {
        int index = getOrderItemIndex(product);
        if (index != -1) {
            OrderItem orderItem = orderItems[index];
            int newQuantity = orderItem.getQuantity() - quantity;

            if (newQuantity <= 0) {
                orderItems[index] = null;
                shiftOrderItems(index);
            } else {
                orderItem.setQuantity(newQuantity);
            }

            return true;
        }

        return false;
    }

    private void shiftOrderItems(int startIndex) {
        for (int i = startIndex; i < itemCount - 1; i++) {
            orderItems[i] = orderItems[i + 1];
        }
        orderItems[itemCount - 1] = null;
        itemCount--;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem != null) {
                totalPrice += orderItem.getTotalPrice();
            }
        }
        return totalPrice;
    }

    private int getOrderItemIndex(Product product) {
        for (int i = 0; i < itemCount; i++) {
            if (orderItems[i] != null && orderItems[i].getProduct().equals(product)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double taxValue(double totalPrice) {
        return totalPrice - totalPrice / (1 + Billable.TAX);
    }

    @Override
    public String bill() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < itemCount; i++) {
            OrderItem orderItem = orderItems[i];
            sb.append(String.format("#%d: %s%n", i + 1, orderItem.bill()));
        }
        double totalTax = taxValue(getTotalPrice());
        sb.append(String.format("TOTAL = %.1f | Tax: %.1f", getTotalPrice(), totalTax).replaceAll(",", "."));
        return sb.toString();
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }


    @Override
    public int compareTo(Order other) {
        if (other == null) {
            throw new NullPointerException();
        }

        int dateComparison = other.getOrderDate().compareTo(getOrderDate());
        if (dateComparison != 0) {
            return dateComparison;
        }

        return Double.compare(other.getTotalPrice(), getTotalPrice());
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public OrderItem[] getOrderItems() {
        OrderItem[] items = new OrderItem[itemCount];
        System.arraycopy(orderItems, 0, items, 0, itemCount);
        return items;
    }


    public int getItemCount() {
        return itemCount;
    }
}
