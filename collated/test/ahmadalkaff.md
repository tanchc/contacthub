# ahmadalkaff
###### /java/guitests/guihandles/PersonCardHandle.java
``` java
        Region phonesContainer = getChildNode(PHONES_FIELD_ID);
        this.phoneLabels = phonesContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());

        Region emailsContainer = getChildNode(EMAILS_FIELD_ID);
        this.emailLabels = emailsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());
```
###### /java/seedu/address/logic/commands/AddCommandTest.java
``` java
        @Override
        public void sortPersonListByName() {
            fail("This method should not be called.");
        }
```
###### /java/seedu/address/logic/commands/DeleteTaskCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstTaskOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBookTasks;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ReadOnlyTask;


/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredTaskList_success() throws Exception {
        ReadOnlyTask taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = prepareCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredTaskList_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredTaskList_success() throws Exception {
        showFirstTaskOnly(model);

        ReadOnlyTask taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = prepareCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFirstTaskOnly(model);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Returns a {@code DeleteTaskCommand} with the parameter {@code index}.
     */
    private DeleteTaskCommand prepareCommand(Index index) {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(index);
        deleteTaskCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return deleteTaskCommand;
    }

    /**
     * Updates {@code model}'s filtered list to show no tasks.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assert model.getFilteredTaskList().isEmpty();
    }

}
```
###### /java/seedu/address/logic/commands/EditTaskCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstTaskOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBookTasks;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTaskCommand.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredTaskList_success() throws Exception {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = prepareCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredTaskList_success() throws Exception {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        ReadOnlyTask lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withAppointment(VALID_APPOINTMENT_EVENT).withDate(VALID_DATE_EVENT)
                .withStartTime(VALID_START_TIME_EVENT).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withAppointment(VALID_APPOINTMENT_EVENT)
                .withDate(VALID_DATE_EVENT).withStartTime(VALID_START_TIME_EVENT).build();
        EditTaskCommand editTaskCommand = prepareCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredTaskList_success() {
        EditTaskCommand editTaskCommand = prepareCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        ReadOnlyTask editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredTaskList_success() throws Exception {
        showFirstTaskOnly(model);

        ReadOnlyTask taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withAppointment(VALID_APPOINTMENT_EVENT).build();
        EditTaskCommand editTaskCommand = prepareCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withAppointment(VALID_APPOINTMENT_EVENT).build());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withAppointment(VALID_APPOINTMENT_EVENT).build();
        EditTaskCommand editTaskCommand = prepareCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered task list where index is larger than size of filtered task list,
     * but smaller than size of ContactHub
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showFirstTaskOnly(model);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        EditTaskCommand editTaskCommand = prepareCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withAppointment(VALID_APPOINTMENT_EVENT).build());

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_MEETING);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_MEETING);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        // assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_MEETING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_INTERVIEW)));
    }

    /**
     * Returns an {@code EditTaskCommand} with parameters {@code index} and {@code descriptor}
     */
    private EditTaskCommand prepareCommand(Index index, EditTaskDescriptor descriptor) {
        EditTaskCommand editTaskCommand = new EditTaskCommand(index, descriptor);
        editTaskCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return editTaskCommand;
    }
}
```
###### /java/seedu/address/logic/commands/ListModuleCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_GER1000;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.BOB_EDITED;
import static seedu.address.testutil.TypicalPersons.CARRIE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookPersons;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

public class ListModuleCommandTest {
    private Model model;
    private Model expectedModel;
    private ListModuleCommand listModuleCommand;
    private ArrayList<Module> moduleList;
    private ArrayList<String> tempList;
    private String expectedMessage;

    public final String listModuleMessage = listModuleCommand.MESSAGE_SUCCESS + "\n" + "Module names: ";

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookPersons(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        moduleList = new ArrayList<>();
        tempList = new ArrayList<>();
        listModuleCommand = new ListModuleCommand();
        listModuleCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        for (ReadOnlyPerson person : model.getAddressBook().getPersonList()) {
            for (Module mod : person.getModules()) {
                if (!moduleList.contains(mod)) {
                    moduleList.add(mod);
                }
            }
        }
        for (Module m : moduleList) {
            tempList.add(m.moduleName);
        }
        Collections.sort(tempList);

        expectedMessage = listModuleMessage + tempList.toString().replace(",", "]").replace(" ", " [");

    }

    @Test
    public void execute_showsCorrectModuleList() {
        assertCommandSuccess(listModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNewPersonWithNewModule_showsCorrectModuleList()
            throws DuplicatePersonException {
        model.addPerson(AMY);
        expectedModel.addPerson(AMY);
        tempList.add(VALID_MOD_CS2101);
        Collections.sort(tempList);
        expectedMessage = listModuleMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNewPersonWithNoModule_showsCorrectModList()
            throws DuplicatePersonException {
        model.addPerson(CARRIE);
        expectedModel.addPerson(CARRIE);
        expectedMessage = listModuleMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editPersonWithPresentModule_showsCorrectModuleList()
            throws DuplicatePersonException, PersonNotFoundException {
        model.addPerson(BOB);
        expectedModel.addPerson(BOB);
        tempList.add(VALID_MOD_GER1000);
        tempList.add(VALID_MOD_CS2101);

        model.updatePerson(BOB, BOB_EDITED);
        expectedModel.updatePerson(BOB, BOB_EDITED);
        tempList.remove(VALID_MOD_GER1000);
        tempList.add(VALID_MOD_CS2103);
        Collections.sort(tempList);
        expectedMessage = listModuleMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deletePersonWithUniqueModule_showsCorrectModuleList()
            throws DuplicatePersonException, PersonNotFoundException {
        model.addPerson(AMY);
        model.addPerson(BOB);
        model.addPerson(CARRIE);
        expectedModel.addPerson(AMY);
        expectedModel.addPerson(BOB);
        expectedModel.addPerson(CARRIE);
        tempList.add(VALID_MOD_CS2101);
        tempList.add(VALID_MOD_GER1000);

        model.deletePerson(BOB);
        expectedModel.deletePerson(BOB);
        tempList.remove(VALID_MOD_GER1000);
        Collections.sort(tempList);
        expectedMessage = listModuleMessage + tempList.toString().replace(",", "]").replace(" ", " [");
        assertCommandSuccess(listModuleCommand, model, expectedMessage, expectedModel);
    }
}
```
###### /java/seedu/address/logic/commands/SortCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookPersons;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;
    private SortCommand sortCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookPersons(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        sortCommand = new SortCommand();
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_sortIsNotFiltered_showsSameList() {
        assertCommandSuccess(sortCommand, model, sortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortIsFiltered_showsEverything() {
        showFirstPersonOnly(model);
        assertCommandSuccess(sortCommand, model, sortCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
```
###### /java/seedu/address/logic/parser/AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_listModule() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_ALIAS) instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD + " 3") instanceof ListModuleCommand);
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_ALIAS + " 3") instanceof ListModuleCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_ALIAS) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " 3") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_ALIAS + " 3") instanceof SortCommand);
    }
```
###### /java/seedu/address/logic/parser/ParserUtilTest.java
``` java
    @Test
    public void parsePhones_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parsePhones(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parsePhones_collectionWithValidPhones_returnsPhoneSet() throws Exception {
        Set<Phone> actualPhoneSet = ParserUtil.parsePhones(Arrays.asList(VALID_PHONE_1, VALID_PHONE_2));
        Set<Phone> expectedPhoneSet = new HashSet<>(Arrays.asList(new Phone(VALID_PHONE_1), new Phone(VALID_PHONE_2)));

        assertEquals(expectedPhoneSet, actualPhoneSet);
    }
```
###### /java/seedu/address/logic/parser/ParserUtilTest.java
``` java
    @Test
    public void parseEmails_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseEmails(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseEmails_collectionWithValidEmails_returnsEmailSet() throws Exception {
        Set<Email> actualEmailSet = ParserUtil.parseEmails(Arrays.asList(VALID_EMAIL_1, VALID_EMAIL_2));
        Set<Email> expectedEmailSet = new HashSet<>(Arrays.asList(new Email(VALID_EMAIL_1), new Email(VALID_EMAIL_2)));

        assertEquals(expectedEmailSet, actualEmailSet);
    }
```
###### /java/seedu/address/testutil/PersonBuilder.java
``` java
            Set<Phone> defaultPhones = SampleDataUtil.getPhoneSet(DEFAULT_PHONES);
```
###### /java/seedu/address/testutil/PersonBuilder.java
``` java
            Set<Email> defaultEmails = SampleDataUtil.getEmailSet(DEFAULT_EMAILS);
```
###### /java/seedu/address/testutil/PersonUtil.java
``` java
        person.getPhones().stream().forEach(
            s -> sb.append(PREFIX_PHONE + s.value + " ")
        );
```
###### /java/seedu/address/testutil/PersonUtil.java
``` java
        person.getEmails().stream().forEach(
            s -> sb.append(PREFIX_EMAIL + s.value + " ")
        );
```
