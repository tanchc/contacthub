package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {

    private ObjectProperty<Name> name;
    private ObjectProperty<PhoneList> phones;
    private ObjectProperty<Birthday> birthday;
    private ObjectProperty<EmailList> emails;
    private ObjectProperty<Address> address;
    private ObjectProperty<Photo> photo;

    private ObjectProperty<UniqueTagList> tags;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Set<Phone> phones, Birthday birthday, Set<Email> emails, Address address, Photo photo,
                  Set<Tag> tags) {
        requireAllNonNull(name, phones, birthday, emails, address, photo, tags);
        this.name = new SimpleObjectProperty<>(name);
        this.phones = new SimpleObjectProperty<>(new PhoneList(phones));
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.emails = new SimpleObjectProperty<>(new EmailList(emails));
        this.address = new SimpleObjectProperty<>(address);
        this.photo = new SimpleObjectProperty<>(photo);
        // protect internal tags from changes in the arg list
        this.tags = new SimpleObjectProperty<>(new UniqueTagList(tags));
    }

    /**
     * Creates a copy of the given ReadOnlyPerson.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhones(), source.getBirthday(), source.getEmails(), source.getAddress(),
                source.getPhoto(), source.getTags());
    }

    public void setName(Name name) {
        this.name.set(requireNonNull(name));
    }

    @Override
    public ObjectProperty<Name> nameProperty() {
        return name;
    }

    @Override
    public Name getName() {
        return name.get();
    }

    /**
     * Returns an immutable phone set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Phone> getPhones() {
        return Collections.unmodifiableSet(phones.get().toSet());
    }

    public ObjectProperty<PhoneList> phoneProperty() {
        return phones;
    }

    /**
     * Replaces this person's phones with the phones in the argument phone set.
     */
    public void setPhones(Set<Phone> replacement) {
        phones.set(new PhoneList(replacement));
    }

    public void setBirthday(Birthday birthday) {
        this.birthday.set(requireNonNull(birthday));
    }

    @Override
    public ObjectProperty<Birthday> birthdayProperty() {
        return birthday;
    }

    @Override
    public Birthday getBirthday() {
        return birthday.get();
    }

    /**
     * Returns an immutable email set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Email> getEmails() {
        return Collections.unmodifiableSet(emails.get().toSet());
    }

    public ObjectProperty<EmailList> emailProperty() {
        return emails;
    }

    /**
     * Replaces this person's phones with the phones in the argument email set.
     */
    public void setEmails(Set<Email> replacement) {
        emails.set(new EmailList(replacement));
    }

    public void setAddress(Address address) {
        this.address.set(requireNonNull(address));
    }

    @Override
    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public Address getAddress() {
        return address.get();
    }

    public void setPhoto(Photo photo) {
        this.photo.set(requireNonNull(photo));
    }

    @Override
    public ObjectProperty<Photo> photoProperty() {
        return photo;
    }

    @Override
    public Photo getPhoto() {
        return photo.get();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get().toSet());
    }

    public ObjectProperty<UniqueTagList> tagProperty() {
        return tags;
    }

    /**
     * Replaces this person's tags with the tags in the argument tag set.
     */
    public void setTags(Set<Tag> replacement) {
        tags.set(new UniqueTagList(replacement));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phones, birthday, emails, address, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
