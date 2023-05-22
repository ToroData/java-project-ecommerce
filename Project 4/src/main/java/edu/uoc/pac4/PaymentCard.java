package edu.uoc.pac4;

import java.time.LocalDate;

/**
 * Represents a payment card.
 * Implements the Cloneable interface to enable cloning of PaymentCard objects.
 * @author Ricard Santiago Raigad García
 * @date 05-22-2023
 * @version 1.0
 */
public class PaymentCard implements Cloneable {

    private String number;
    private LocalDate expireDate;

    /**
     * Constructs a PaymentCard object with the specified number and expire date.
     *
     * @param number     the number of the payment card
     * @param expireDate the expiry date of the payment card
     */
    public PaymentCard(String number, LocalDate expireDate) {
        setNumber(number);
        setExpireDate(expireDate);
    }

    /**
     * Gets the number of the payment card.
     *
     * @return the card number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number of the payment card.
     *
     * @param number the card number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the expiry date of the payment card.
     *
     * @return the expiry date
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the expiry date of the payment card.
     *
     * @param expireDate the expiry date to set
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Creates and returns a copy of this PaymentCard object.
     *
     * @return a clone of this instance
     * @throws CloneNotSupportedException if cloning is not supported for this object
     */
    @Override
    public PaymentCard clone() throws CloneNotSupportedException {
        return (PaymentCard) super.clone();
    }

}
