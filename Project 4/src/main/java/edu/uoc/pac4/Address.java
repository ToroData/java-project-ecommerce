package edu.uoc.pac4;

/**
 * Represents an address.
 * Implements the Cloneable interface to enable cloning of Address objects.
 * @author Ricard Santiago Raigad García
 * @date 05-22-2023
 * @version 1.0
 */
public class Address implements Cloneable {

    private String street;
    private String zipCode;

    /**
     * Constructs an Address object with the specified street and zip code.
     *
     * @param street   the street of the address
     * @param zipCode  the zip code of the address
     */
    public Address(String street, String zipCode) {
        setStreet(street);
        setZipCode(zipCode);
    }

    /**
     * Gets the street of the address.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     *
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the zip code of the address.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode the zip code to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Creates and returns a copy of this Address object.
     *
     * @return a clone of this instance
     * @throws CloneNotSupportedException if cloning is not supported for this object
     */
    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

}
