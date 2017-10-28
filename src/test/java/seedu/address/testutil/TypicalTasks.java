package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.task.ReadOnlyTask;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final ReadOnlyTask MEETING = new TaskBuilder().withAppointment("Meeting")
            .withDate("25/11/2017").withStartTime("12:00").build();

    public static final ReadOnlyTask INTERVIEW = new TaskBuilder().withAppointment("Interview")
            .withDate("28/10/2017").withStartTime("10:00").build();

    // Manually added
    public static final ReadOnlyTask LUNCH = new TaskBuilder().withAppointment("Lunch")
            .withDate("03/11/2017").withStartTime("12:00").build();

    public static final String KEYWORD_MATCHING_MEETING = "Meeting"; // A keyword that matches Meeting

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBookTasks() {
        AddressBook ab = new AddressBook();
        for (ReadOnlyTask task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<ReadOnlyTask> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(LUNCH, MEETING, INTERVIEW));
    }
}
