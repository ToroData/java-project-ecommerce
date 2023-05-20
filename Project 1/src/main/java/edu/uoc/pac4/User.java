package edu.uoc.pac4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Represents a user in the system.
 *
 * This class provides methods for managing user information
 *
 * @Author: Ricard Santiago Raigada García
 * @Date: 05/20/2023
 * @Version: 1.0
 */
public class User {
    private String name;
    private String email;
    private LocalDate birthDate;
    private boolean premium;
    private Gender gender;
    private Address address;
    private double debt;

    /**
     * Constructs a User object with the specified name, email, birthdate, gender, and address.
     *
     * @param name       the name of the user
     * @param email      the email of the user
     * @param birthDate  the birthdate of the user
     * @param gender     the gender of the user
     * @param address    the address of the user
     * @throws UserException if there is an error in the user data
     */
    public User(String name, String email, LocalDate birthDate, Gender gender, Address address) throws UserException {
        this.name = name;
        setEmail(email);
        setBirthDate(birthDate);
        this.gender = gender;
        this.address = address;
    }

    /**
     * Constructs a User object with the specified name, email, birth date, and gender.
     *
     * @param name       the name of the user
     * @param email      the email of the user
     * @param birthDate  the birthdate of the user
     * @param gender     the gender of the user
     * @throws UserException if there is an error in the user data
     */
    public User(String name, String email, LocalDate birthDate, Gender gender) throws UserException {
        this.name = name;
        setEmail(email);
        setBirthDate(birthDate);
        this.gender = gender;
        this.address = null;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the gender of the user
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the gender of the user.
     *
     * @return the gender of the user
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     * @throws UserException if the email is not in a valid format
     */
    public void setEmail(String email) throws UserException {
        if (!email.matches(".+@.*\\..*")) {
            throw new UserException(UserException.ERR_INVALID_EMAIL);
        }
        this.email = email;
    }

    /**
     * Returns the birthdate of the user.
     *
     * @return the birthdate of the user
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birthdate of the user.
     *
     * @param birthDate the birthdate of the user
     * @throws UserException if the user is younger than 16 years old
     */
    public void setBirthDate(LocalDate birthDate) throws UserException {
        LocalDate currentDate = LocalDate.now();
        long diffInYears = ChronoUnit.YEARS.between(birthDate, currentDate);
        if (diffInYears < 16) {
            throw new UserException(UserException.ERR_MIN_AGE);
        }
        this.birthDate = birthDate;
    }

    /**
     * Checks if the user is a premium user.
     *
     * @return true if the user is a premium user, false otherwise
     */
    public boolean isPremium() {
        return premium;
    }

    /**
     * Subscribes the user as a premium user. If debt is greater than 0, throw an exception.
     *
     * @throws UserException if the user has debt
     */
    public void subscribe() throws UserException {
        if (debt > 0) {
            throw new UserException(UserException.ERR_PREMIUM_SUBSCRIPTION);
        }
        premium = true;
    }

    /**
     * Unsubscribes the user from the premium subscription. Assign premium to false.
     */
    public void unsubscribe() {
        premium = false;
    }

    /**
     * Returns the debt of the user.
     *
     * @return the debt of the user
     */
    public double getDebt() {
        return debt;
    }

    /**
     * Adds debt to the user's account.
     *
     * @param value the amount of debt to add
     * @throws UserException if the value is not greater than zero
     */
    public void addDebt(double value) throws UserException {
        if (value <= 0) {
            throw new UserException(UserException.ERR_ADD_INVALID_DEBT_VALUE);
        }
        debt += value;
    }

    /**
     * Sets the debt of the user.
     *
     * @param debt the debt of the user
     * @throws UserException if the debt is negative
     */
    private void setDebt(double debt) throws UserException {
        if (debt < 0) {
            throw new UserException(UserException.ERR_ADD_INVALID_DEBT_VALUE);
        }
        this.debt = debt;
    }

    /**
     * Resets the user's debt to zero.
     */
    public void resetDebt() {
        debt = 0.0;
    }

    /**
     * Sets the address of the user.
     *
     * @param address the address of the user
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the address of the user.
     *
     * @return the address of the user
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Checks if this user is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(email, other.email);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tName: ").append(name).append(System.lineSeparator());
        sb.append("\te-mail: ").append(email).append(System.lineSeparator());
        sb.append("\tBirth date: ").append(birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(System.lineSeparator());
        sb.append("\tPremium?: ").append(premium ? "Y" : "N").append(System.lineSeparator());
        sb.append("\tAddress: ").append(address.toString()).append(System.lineSeparator());
        return sb.toString();
    }
}
