# viviantan95
###### \java\seedu\address\model\person\Birthday.java
``` java
/**
 * Represents a Person's Birthday in the address book.
 * Guarantees: immutable, is valid as declared in {@link #isValidBirthday(String)}
 */

public class Birthday {

    public static final String MESSAGE_BIRTHDAY_CONSTRAINTS =
            "Birthday must be in the format of DD/MM/YYYY";

    public static final String BIRTHDAY_VALIDATION_REGEX =
            "\\d{2}/\\d{2}/\\d{4}";

    public final String value;
    private String browserValue;

    //Validates given Birthday.
    public Birthday(String birthday) throws IllegalValueException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!isValidBirthday(trimmedBirthday)) {
            throw new IllegalValueException(MESSAGE_BIRTHDAY_CONSTRAINTS);
        }
        this.value = trimmedBirthday;
        this.browserValue = trimmedBirthday;
    }

    /**
     * Returns true if a given String is a valid Birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(BIRTHDAY_VALIDATION_REGEX);
    }

```
