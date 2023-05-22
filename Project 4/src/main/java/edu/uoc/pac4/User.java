package edu.uoc.pac4;

import java.time.LocalDate;

/**
 * Represents a user.
 * Implements the Cloneable interface to enable cloning of User objects.
 * @author Ricard Santiago Raigad García
 * @date 05-22-2023
 * @version 1.0
 */
public class User implements Cloneable {

    private String name;
    public Address address;
    public PaymentCard paymentCard;

    /**
     * Constructs a User object with the specified name, address, and payment card information.
     *
     * @param name           the name of the user
     * @param street         the street of the user's address
     * @param zipCode        the zip code of the user's address
     * @param cardNumber     the number of the user's payment card
     * @param cardExpireDate the expiry date of the user's payment card
     */
    public User(String name, String street, String zipCode, String cardNumber, LocalDate cardExpireDate) {
        setName(name);
        this.address = new Address(street, zipCode);
        this.paymentCard = new PaymentCard(cardNumber, cardExpireDate);
    }

    /**
     * Gets the name of the user.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the user.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the payment card of the user.
     *
     * @return the payment card
     */
    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    /**
     * Sets the payment card of the user.
     *
     * @param paymentCard the payment card to set
     */
    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    /**
     * Creates and returns a copy of this User object.
     *
     * @return a clone of this instance
     * @throws CloneNotSupportedException if cloning is not supported for this object
     */
    @Override
    public User clone() throws CloneNotSupportedException {
        User clonedUser = (User) super.clone();
        clonedUser.address = this.address.clone();
        clonedUser.paymentCard = this.paymentCard.clone();
        return clonedUser;
    }

}
