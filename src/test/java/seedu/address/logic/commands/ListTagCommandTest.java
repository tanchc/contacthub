package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOB_EDITED;
import static seedu.address.testutil.TypicalPersons.CARRIE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

public class ListTagCommandTest {
    private Model model;
    private Model expectedModel;
    private ListTagCommand listTagCommand;
    private ArrayList<Tag> tagList;
    private ArrayList<String> tempList;
    private String expectedMessage;

    public final String listTagMessage = listTagCommand.MESSAGE_SUCCESS + "\n" + "Tag names: ";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tagList = new ArrayList<>();
        tempList = new ArrayList<>();
        listTagCommand = new ListTagCommand();
        listTagCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        for (ReadOnlyPerson person : model.getAddressBook().getPersonList()) {
            for (Tag tag : person.getTags()) {
                if (!tagList.contains(tag)) {
                    tagList.add(tag);
                }
            }
        }
        for (Tag t : tagList) {
            tempList.add(t.tagName);
        }
        Collections.sort(tempList);

        expectedMessage = listTagMessage + tempList.toString().replace(",", "]").replace(" ", " [");

    }

    @Test
    public void execute_showsCorrectTagList() {
        assertCommandSuccess(listTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNewPersonWithNewTag_showsCorrectTagList()
            throws DuplicatePersonException {
        model.addPerson(AMY);
        expectedModel.addPerson(AMY);
        tempList.add(VALID_TAG_FRIEND);
        Collections.sort(tempList);
        expectedMessage = listTagMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNewPersonWithNoTag_showsCorrectTagList()
            throws DuplicatePersonException {
        model.addPerson(CARRIE);
        expectedModel.addPerson(CARRIE);
        expectedMessage = listTagMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editPersonWithPresentTag_showsCorrectTagList()
            throws DuplicatePersonException, PersonNotFoundException {
        model.addPerson(BOB);
        expectedModel.addPerson(BOB);
        tempList.add(VALID_TAG_HUSBAND);
        tempList.add(VALID_TAG_FRIEND);

        model.updatePerson(BOB, BOB_EDITED);
        expectedModel.updatePerson(BOB, BOB_EDITED);
        tempList.remove(VALID_TAG_HUSBAND);
        tempList.add(VALID_TAG_CHEF);
        Collections.sort(tempList);
        expectedMessage = listTagMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deletePersonWithUniqueTag_showsCorrectTagList()
            throws DuplicatePersonException, PersonNotFoundException {
        model.addPerson(AMY);
        model.addPerson(BOB);
        model.addPerson(CARRIE);
        expectedModel.addPerson(AMY);
        expectedModel.addPerson(BOB);
        expectedModel.addPerson(CARRIE);
        tempList.add(VALID_TAG_FRIEND);
        tempList.add(VALID_TAG_HUSBAND);

        model.deletePerson(BOB);
        expectedModel.deletePerson(BOB);
        tempList.remove(VALID_TAG_HUSBAND);
        Collections.sort(tempList);
        expectedMessage = listTagMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listTagCommand, model, expectedMessage, expectedModel);
    }
}
