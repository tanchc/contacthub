package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;

/**
 * Represents a Task's appointment in the address book.
 */
public class Appointment {

    public static final String MESSAGE_APPOINTMENT_CONSTRAINTS =
            "Appointment name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String APPOINTMENT_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String appointmentName;

    public Appointment(String appointment) throws IllegalValueException {
        requireNonNull(appointment);
        String trimmedAppointment = appointment.trim();
        if (!isValidName(trimmedAppointment)) {
            throw new IllegalValueException(MESSAGE_APPOINTMENT_CONSTRAINTS);
        }
        this.appointmentName = trimmedAppointment;
    }

    /**
     * Returns true if a given string is a valid appointment name.
     */
    public static boolean isValidName(String test) {
        return test.matches(APPOINTMENT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return appointmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.appointmentName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return appointmentName.hashCode();
    }

}
