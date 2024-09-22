package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Remark in the address book.
 * Guarantees: immutable; is valid as declared.
 */
public class Remark {
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns the string representation of the remark.
     *
     * @return The remark as a string.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if two Remark objects are equal.
     *
     * @param other The other object to compare against.
     * @return True if both Remark objects have the same value, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    /**
     * Returns the hash code of the Remark object.
     *
     * @return The hash code of the value.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
