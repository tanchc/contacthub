package seedu.address.model.person;

import java.util.Set;

import javafx.beans.property.ObjectProperty;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

/**
 * A read-only immutable interface for a Person in ContactHub.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    ObjectProperty<Name> nameProperty();
    Name getName();
    ObjectProperty<PhoneList> phoneProperty();
    Set<Phone> getPhones();
    String getBrowserPhones();
    ObjectProperty<Birthday> birthdayProperty();
    Birthday getBirthday();
    String getBrowserEmails();
    ObjectProperty<EmailList> emailProperty();
    Set<Email> getEmails();
    ObjectProperty<Address> addressProperty();
    Address getAddress();
    String getBrowserModules();
    ObjectProperty<UniqueModuleList> moduleProperty();
    Set<Module> getModules();
    ObjectProperty<Photo> photoProperty();
    Photo getPhoto();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhones().equals(this.getPhones())
                && other.getBirthday().equals(this.getBirthday())
                && other.getEmails().equals(this.getEmails())
                && other.getAddress().equals(this.getAddress()))
                && other.getPhoto().equals(this.getPhoto());
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone Number(s): ")
                .append(getPhones().toString())
                .append(" Birthday: ")
                .append(getBirthday())
                .append(" Email(s): ")
                .append(getEmails().toString())
                .append(" Address: ")
                .append(getAddress())
                .append(" Photo URL: ")
                .append(getPhoto())
                .append(" Module(s): ");
        getModules().forEach(builder::append);
        return builder.toString();
    }

}
