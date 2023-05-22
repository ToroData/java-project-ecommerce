package edu.uoc.pac4;
import edu.uoc.pac4.exception.OrderException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

/**
 * Represents a batch of orders.
 */
public class OrderBatch {
    private static final String MSG_ERR_NULL = "[ERROR] The Order object cannot be null";
    private String name;
    private String description;
    private final int MAX_SIZE;
    private final Set<Order> orders;

    /**
     * Constructs an OrderBatch with the specified name and description.
     *
     * @param name        the name of the order batch
     * @param description the description of the order batch
     */
    public OrderBatch(String name, String description) {
        this.name = name;
        this.description = description;
        this.MAX_SIZE = 1000;
        this.orders = new HashSet<>(MAX_SIZE);
    }

    /**
     * Returns the name of the order batch.
     *
     * @return the name of the order batch
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the order batch.
     *
     * @param name the name of the order batch
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the order batch.
     *
     * @return the description of the order batch
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the order batch.
     *
     * @param description the description of the order batch
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the maximum size of the order batch.
     *
     * @return the maximum size of the order batch
     */
    public int getMaxSize() {
        return MAX_SIZE;
    }

    /**
     * Returns a list of orders in the order batch.
     *
     * @return a list of orders in the order batch
     */
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Adds an order to the order batch.
     *
     * @param order the order to add
     * @return true if the order was added successfully, false if the order batch is full
     * @throws NullPointerException if the order is null
     */
    public boolean addOrder(Order order) {
        if (order == null) {
            throw new NullPointerException(MSG_ERR_NULL);
        }

        if (isFull()) {
            return false;
        }

        return orders.add(order);
    }

    /**
     * Removes an order from the order batch.
     *
     * @param order the order to remove
     * @return true if the order was removed successfully, false if the order was not found
     * @throws NullPointerException if the order is null
     */
    public boolean remove(Order order) {
        if (order == null) {
            throw new NullPointerException(MSG_ERR_NULL);
        }

        return orders.remove(order);
    }

    /**
     * Removes all orders from the order batch.
     */
    public void remove() {
        orders.clear();
    }

    /**
     * Checks if an order exists in the order batch.
     *
     * @param order the order to check
     * @return true if the order exists in the order batch, false otherwise
     */
    public boolean exists(Order order) {
        return orders.contains(order);
    }

    /**
     * Checks if the order batch is empty.
     *
     * @return true if the order batch is empty, false otherwise
     */
    public boolean isEmpty() {
        return orders.isEmpty();
    }

    /**
     * Checks if the order batch is full.
     *
     * @return true if the order batch is full, false otherwise
     */
    public boolean isFull() {
        return orders.size() == MAX_SIZE;
    }

    /**
     * Delivers all orders in the order batch that have an order date after the specified date.
     *
     * @param orderDate the date to compare the order dates with
     */
    public void deliverOrdersAfterDate(LocalDate orderDate) {
        orders.stream()
                .filter(order -> order.getOrderDate().isAfter(orderDate))
                .forEach(order -> {
                    try {
                        order.setDeliveryDate(LocalDate.now());
                    } catch (OrderException e) {
                        throw new RuntimeException(e);
                    }
                });
    }


    /**
     * Returns a list of the largest orders in the order batch.
     *
     * @return a list of the largest orders in the order batch
     */
    public List<Order> getLargestOrders() {
        Optional<Double> maxPrice = orders.stream()
                .map(Order::getTotalPrice)
                .max(Comparator.naturalOrder());

        return maxPrice.map(aDouble -> orders.stream()
                .filter(order -> Math.abs(order.getTotalPrice() - aDouble) < 0.001)
                .sorted()
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    /**
     * Calculates the total income generated by a specific product in the order batch.
     *
     * @param product the product to calculate the income for
     * @return the total income generated by the product
     */
    public double auditIncomeByProduct(Product product) {
        double totalIncome = 0.0;
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getProduct().equals(product)) {
                    double income = orderItem.getProduct().getPrice() * orderItem.getQuantity();
                    totalIncome += income;
                }
            }
        }
        return totalIncome;

    }

    /**
     * Returns a string representation of the order batch.
     *
     * @return a string representation of the order batch
     */
    @Override
    public String toString() {
        List<Order> sortedOrders = new ArrayList<>(orders);
        sortedOrders.sort(Comparator.comparing(Order::getOrderDate));

        StringBuilder sb = new StringBuilder();
        for (Order order : sortedOrders) {
            sb.append("###").append(System.lineSeparator());
            sb.append(order.bill()).append(System.lineSeparator());
        }
        sb.append("###");

        String input = sb.toString();
        String regex = "(Tax: \\d+\\.\\d)0\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.replaceAll("$1");
    }


}
