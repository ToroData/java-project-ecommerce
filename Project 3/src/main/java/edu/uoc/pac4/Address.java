package edu.uoc.pac4;

import edu.uoc.pac4.exception.AddressException;

import java.util.Objects;

/**
 * This class represents an address.
 *
 * Represents a physical address with multiple properties
 *
 * @Author: Ricard Santiago Raigada García
 * @Date: 05/20/2023
 * @Version: 1.0
 */

public class Address {
    private String street;
    private int number;
    private String zipCode;
    private String city;

    /**
     * Constructs a new Address object with the specified street, number, zip code, and city.
     *
     * @param street  the street name.
     * @param number  the street number.
     * @param zipCode the zip code.
     * @param city    the city name.
     */
    public Address(String street, int number, String zipCode, String city) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * Returns the street name of this address.
     *
     * @return the street name.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street name of this address.
     *
     * @param street the street name to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the street number of this address.
     *
     * @return the street number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the street number of this address.
     *
     * @param number the street number to set.
     * @throws AddressException if the street number is not greater than zero.
     */
    public void setNumber(int number) throws AddressException {
        if (number <= 0) {
            throw new AddressException(AddressException.ERR_STREET_NUMBER);
        }
        this.number = number;
    }

    /**
     * Returns the zip code of this address.
     *
     * @return the zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of this address.
     *
     * @param zipCode the zip code to set.
     * @throws AddressException if the zip code is not alphanumeric.
     */
    public void setZipCode(String zipCode) throws AddressException {
        if (!zipCode.matches("[a-zA-Z0-9]+")) {
            throw new AddressException(AddressException.ERR_INVALID_ZIPCODE);
        }
        this.zipCode = zipCode;
    }

    /**
     * Returns the city name of this address.
     *
     * @return the city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns whether the address is international.
     *
     * @return true if the address is international, false otherwise.
     */
    public boolean isInternational() {
        return zipCode.length() != 5 || !zipCode.matches("\\d+");
    }

    /**
     * Sets the city name of this address.
     *
     * @param city the city name to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Compares this address to the specified object for equality.
     *
     * @param obj the object to compare.
     * @return true if the addresses are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(street, other.street) &&
                Objects.equals(number, other.number) &&
                Objects.equals(zipCode, other.zipCode);
    }

    /**
     * Returns a string representation of this address.
     *
     * @return a string representation of this address.
     */
    @Override
    public String toString() {
        return String.format("%s, %d, %s (%s)", street, number, city, zipCode);
    }

}
