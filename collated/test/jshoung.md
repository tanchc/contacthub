# jshoung
###### \java\guitests\BusWindowTest.java
``` java

package guitests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.BusWindowHandle;
import seedu.address.logic.commands.BusCommand;

public class BusWindowTest extends AddressBookGuiTest {
    private static final String ERROR_MESSAGE = "ATTENTION!!!! : On some computers, this test may fail when run on "
            + "non-headless mode as FxRobot#clickOn(Node, MouseButton...) clicks on the wrong location. We suspect "
            + "that this is a bug with TestFX library that we are using. If this test fails, you have to run your "
            + "tests on headless mode. See UsingGradle.adoc on how to do so.";

    @Test
    public void openBusWindow() {
        //use command box
        runCommand(BusCommand.COMMAND_WORD);
        assertBusWindowOpen();
    }

    /**
     * Asserts that the bus window is open, and closes it after checking.
     */
    private void assertBusWindowOpen() {
        assertTrue(ERROR_MESSAGE, BusWindowHandle.isWindowPresent());
        guiRobot.pauseForHuman();

        new BusWindowHandle(guiRobot.getStage(BusWindowHandle.BUS_WINDOW_TITLE)).close();
        mainWindowHandle.focus();
    }

    /**
     * Asserts that the bus window isn't open. For future use.
     */
    private void assertBusWindowNotOpen() {
        assertFalse(ERROR_MESSAGE, BusWindowHandle.isWindowPresent());
    }

}
```
###### \java\guitests\guihandles\BusWindowHandle.java
``` java

package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;

/**
 * A handle to the {@code BusWindow} of the application.
 */
public class BusWindowHandle extends StageHandle {

    public static final String BUS_WINDOW_TITLE = "Bus Routes";

    private static final String BUS_WINDOW_BROWSER_ID = "#browser";

    public BusWindowHandle(Stage busWindowStage) {
        super(busWindowStage);
    }

    /**
     * Returns true if a bus window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(BUS_WINDOW_TITLE);
    }
}
```
###### \java\guitests\guihandles\MapWindowHandle.java
``` java

package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;

/**
 * A handle to the {@code MapWindow} of the application.
 */
public class MapWindowHandle extends StageHandle {

    public static final String MAP_WINDOW_TITLE = "NUS Map";

    private static final String MAP_WINDOW_BROWSER_ID = "#browser";

    public MapWindowHandle(Stage mapWindowStage) {
        super(mapWindowStage);
    }

    /**
     * Returns true if a map window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(MAP_WINDOW_TITLE);
    }
}
```
###### \java\guitests\MapWindowTest.java
``` java

package guitests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.MapWindowHandle;
import seedu.address.logic.commands.MapCommand;

public class MapWindowTest extends AddressBookGuiTest {
    private static final String ERROR_MESSAGE = "ATTENTION!!!! : On some computers, this test may fail when run on "
            + "non-headless mode as FxRobot#clickOn(Node, MouseButton...) clicks on the wrong location. We suspect "
            + "that this is a bug with TestFX library that we are using. If this test fails, you have to run your "
            + "tests on headless mode. See UsingGradle.adoc on how to do so.";

    @Test
    public void openMapWindow() {
        //use command box
        runCommand(MapCommand.COMMAND_WORD);
        assertMapWindowOpen();
    }

    /**
     * Asserts that the map window is open, and closes it after checking.
     */
    private void assertMapWindowOpen() {
        assertTrue(ERROR_MESSAGE, MapWindowHandle.isWindowPresent());
        guiRobot.pauseForHuman();

        new MapWindowHandle(guiRobot.getStage(MapWindowHandle.MAP_WINDOW_TITLE)).close();
        mainWindowHandle.focus();
    }

    /**
     * Asserts that the map window isn't open. For future use.
     */
    private void assertMapWindowNotOpen() {
        assertFalse(ERROR_MESSAGE, MapWindowHandle.isWindowPresent());
    }

}
```
###### \java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void deleteModule(Module module) throws DuplicatePersonException, PersonNotFoundException {
            fail("This method should not be called.");
        }

    }

    /**
     * A Model stub that always throw a DuplicatePersonException when trying to add a person.
     */
    private class ModelStubThrowingDuplicatePersonException extends ModelStub {
        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addPerson(ReadOnlyPerson person) throws DuplicatePersonException {
            personsAdded.add(new Person(person));
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
```
###### \java\seedu\address\logic\commands\BusCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.BusCommand.SHOWING_BUS_MESSAGE;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ShowBusRequestEvent;
import seedu.address.ui.testutil.EventsCollectorRule;

public class BusCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_bus_success() {
        CommandResult result = new BusCommand().execute();
        assertEquals(SHOWING_BUS_MESSAGE, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowBusRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
```
###### \java\seedu\address\logic\commands\MapCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.MapCommand.SHOWING_MAP_MESSAGE;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ShowMapRequestEvent;
import seedu.address.ui.testutil.EventsCollectorRule;

public class MapCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_map_success() {
        CommandResult result = new MapCommand().execute();
        assertEquals(SHOWING_MAP_MESSAGE, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowMapRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
```
###### \java\seedu\address\logic\commands\SummaryCommandTest.java
``` java
package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.SummaryCommand.SHOWING_SUMMARY_MESSAGE;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ShowSummaryRequestEvent;
import seedu.address.ui.testutil.EventsCollectorRule;

public class SummaryCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    @Test
    public void execute_summary_success() {
        CommandResult result = new SummaryCommand().execute();
        assertEquals(SHOWING_SUMMARY_MESSAGE, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowSummaryRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 2);
    }
}
```
###### \java\seedu\address\ui\BrowserPanelTest.java
``` java
        // associated web page of a person
        postNow(selectionChangedEventStub);
        URL expectedPersonUrl = MainApp.class.getResource(FXML_FILE_FOLDER + ADDRESS_PAGE);
        assertEquals(expectedPersonUrl, browserPanelHandle.getLoadedUrl());
```
