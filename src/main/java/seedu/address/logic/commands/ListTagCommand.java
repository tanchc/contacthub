package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.tag.Tag;

/**
 * Lists all tags in the address book to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "listtag";
    public static final String COMMAND_ALIAS = "lt";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    private ArrayList<Tag> tagList = new ArrayList<>();
    private ArrayList<String> tempList = new ArrayList<>();
    private String tagString = "Tag names: ";


    @Override
    public CommandResult execute() {
        extractAllTags();
        sortTags();
        return new CommandResult(MESSAGE_SUCCESS + "\n" + tagString + formatTagString());
    }

    /**
     * Formats the tag list into a string
     */
    private String formatTagString() {
        return tempList.toString().replace(",", "]").replace(" ", " [");
    }

    /**
     * Sorts the tags alphabetically
     */
    private void sortTags() {
        for (Tag t : tagList) {
            tempList.add(t.tagName);
        }

        Collections.sort(tempList);
    }

    /**
     * Extract all the tags from the address book and stores it in a list
     */
    private void extractAllTags() {
        for (ReadOnlyPerson person : model.getAddressBook().getPersonList()) {
            for (Tag tag : person.getTags()) {
                if (!tagList.contains(tag)) {
                    tagList.add(tag);
                }
            }
        }
    }

}
