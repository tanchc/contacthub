package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Photo;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        try {
            return new Person[] {
                new Person(new Name("Alex Yeoh"), getPhoneSet("87438807"), new Birthday("17/05/1995"),
                    getEmailSet("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Photo("images/defaultPhoto.png"), getTagSet("friends")),
                new Person(new Name("Bernice Yu"), getPhoneSet("99272758"), new Birthday("08/01/1991"),
                    getEmailSet("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new Photo("images/defaultPhoto.png"), getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), getPhoneSet("93210283"),
                    new Birthday("20/11/1987"), getEmailSet("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Photo("images/defaultPhoto.png"), getTagSet("neighbours")),
                new Person(new Name("David Li"), getPhoneSet("91031282"), new Birthday("29/02/1997"),
                    getEmailSet("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Photo("images/defaultPhoto.png"), getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), getPhoneSet("92492021"),
                    new Birthday("01/01/1976"), getEmailSet("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Photo("images/defaultPhoto.png"), getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), getPhoneSet("92624417"),
                    new Birthday("13/09/1966"), getEmailSet("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Photo("images/defaultPhoto.png"), getTagSet("colleagues"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAb = new AddressBook();
            for (Person samplePerson : getSamplePersons()) {
                sampleAb.addPerson(samplePerson);
            }
            return sampleAb;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }

    /**
     * Returns a phone set containing the list of strings given.
     */
    public static Set<Phone> getPhoneSet(String... strings) throws IllegalValueException {
        HashSet<Phone> phones = new HashSet<>();
        for (String s : strings) {
            phones.add(new Phone(s));
        }

        return phones;
    }

    /**
     * Returns a email set containing the list of strings given.
     */
    public static Set<Email> getEmailSet(String... strings) throws IllegalValueException {
        HashSet<Email> emails = new HashSet<>();
        for (String s : strings) {
            emails.add(new Email(s));
        }

        return emails;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) throws IllegalValueException {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

}
