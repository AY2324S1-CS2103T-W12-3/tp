package seedu.application.model.job;

import static java.util.Objects.requireNonNull;

import seedu.application.commons.util.AppUtil;
public class Address {
    public static final String MESSAGE_CONSTRAINTS =
            "Address descriptions should adhere to the following constraints:\n"
                    + "1. The first character of the address must not be a whitespace\n";

    public static final String TO_ADD_ADDRESS = "TO_ADD_ADDRESS";
    public static final Address DEFAULT_ADDRESS = new Address(TO_ADD_ADDRESS);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String address;

    public Address(String address) {
        requireNonNull(address);
        AppUtil.checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        this.address = address.toUpperCase();
    }

    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Address)) {
            return false;
        }

        Address otherAddress = (Address) other;
        return address.equals(otherAddress.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}
