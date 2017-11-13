# jshoung
###### \java\seedu\address\commons\events\ui\ClearPersonSelectionEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to clear the person selection.
 */
public class ClearPersonSelectionEvent extends BaseEvent {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\events\ui\GetModuleRequestEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to view the module page.
 */
public class GetModuleRequestEvent extends BaseEvent {
    public final String module;

    public GetModuleRequestEvent(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\events\ui\ShowBusRequestEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to view the bus page.
 */
public class ShowBusRequestEvent extends BaseEvent {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\events\ui\ShowMapRequestEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to view the map page.

 */
public class ShowMapRequestEvent extends BaseEvent {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\events\ui\ShowSummaryRequestEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to view the command summary.
 */
public class ShowSummaryRequestEvent extends BaseEvent {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\events\ui\VenueRequestEvent.java
``` java
package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to view the venue page.
 */
public class VenueRequestEvent extends BaseEvent {
    public final String venue;

    public VenueRequestEvent(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
```
###### \java\seedu\address\commons\util\FxViewUtil.java
``` java
    /**
     * Returns the currently loaded {@code image} in the {@code ImageView}. For future test implementation
     */
    public static Image getImage(ImageView imageview) {
        return imageview.getImage();
    }

}
```
###### \java\seedu\address\logic\commands\BusCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ShowBusRequestEvent;

/**
 * Opens bus route map for NUS
 */
public class BusCommand extends Command {

    public static final String COMMAND_WORD = "bus";
    public static final String COMMAND_ALIAS = "b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows bus routes for NUS.\n"
            + "Example: " + COMMAND_WORD;

    static final String SHOWING_BUS_MESSAGE = "Opened bus window.";

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ShowBusRequestEvent());
        return new CommandResult(SHOWING_BUS_MESSAGE);
    }
}
```
###### \java\seedu\address\logic\commands\GetModuleCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ClearPersonSelectionEvent;
import seedu.address.commons.events.ui.GetModuleRequestEvent;

/**
 *
 * Opens NUSMods webpage to get module information.
 *
 */
public class GetModuleCommand extends Command {
    public static final String COMMAND_WORD = "getmodule";
    public static final String COMMAND_ALIAS = "gm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows module information.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " CS2101" + "\n"
            + "Example: " + COMMAND_ALIAS + " CS2103T";

    public static final String SHOWING_MODULE_MESSAGE = "Loaded module information for: ";

    private final String module;

    public GetModuleCommand(String module) {
        this.module = module;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new GetModuleRequestEvent(module));
        EventsCenter.getInstance().post(new ClearPersonSelectionEvent());
        return new CommandResult(SHOWING_MODULE_MESSAGE + module);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetModuleCommand // instanceof handles nulls
                && this.module.equals(((GetModuleCommand) other).module)); // state check
    }
}
```
###### \java\seedu\address\logic\commands\MapCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ShowMapRequestEvent;

/**
 * Format full help instructions for every command for display.
 */
public class MapCommand extends Command {

    public static final String COMMAND_WORD = "map";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the NUS map.\n"
            + "Example: " + COMMAND_WORD;

    static final String SHOWING_MAP_MESSAGE = "Opened map window.";

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ShowMapRequestEvent());
        return new CommandResult(SHOWING_MAP_MESSAGE);
    }
}
```
###### \java\seedu\address\logic\commands\SummaryCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ClearPersonSelectionEvent;
import seedu.address.commons.events.ui.ShowSummaryRequestEvent;

/**
 * Opens ContactHub command summary
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";
    public static final String COMMAND_ALIAS = "sm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows ContactHub command summary.\n"
            + "Example: " + COMMAND_WORD;

    static final String SHOWING_SUMMARY_MESSAGE = "Opened command summary.";

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ClearPersonSelectionEvent());
        EventsCenter.getInstance().post(new ShowSummaryRequestEvent());
        return new CommandResult(SHOWING_SUMMARY_MESSAGE);
    }
}
```
###### \java\seedu\address\logic\commands\VenueCommand.java
``` java
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ClearPersonSelectionEvent;
import seedu.address.commons.events.ui.VenueRequestEvent;

/**
 *
 * Opens NUSMods webpage to get venue information.
 *
 */
public class VenueCommand extends Command {
    public static final String COMMAND_WORD = "venue";
    public static final String COMMAND_ALIAS = "v";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows venue occupancy information.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " COM1-0114" + "\n"
            + "Example: " + COMMAND_ALIAS + " COM1-0114";

    public static final String SHOWING_VENUE_MESSAGE = "Loaded venue information for: ";

    private final String venue;

    public VenueCommand(String venue) {
        this.venue = venue;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new VenueRequestEvent(venue));
        EventsCenter.getInstance().post(new ClearPersonSelectionEvent());
        return new CommandResult(SHOWING_VENUE_MESSAGE + venue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueCommand // instanceof handles nulls
                && this.venue.equals(((VenueCommand) other).venue)); // state check
    }
}
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        switch (commandWord.toLowerCase()) {
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case HelpCommand.COMMAND_ALIAS:
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case BusCommand.COMMAND_WORD:
        case BusCommand.COMMAND_ALIAS:
            return new BusCommand();
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case MapCommand.COMMAND_WORD:
        case MapCommand.COMMAND_ALIAS:
            return new MapCommand();
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case GetModuleCommand.COMMAND_WORD:
        case GetModuleCommand.COMMAND_ALIAS:
            return new GetModuleCommandParser().parse(arguments);
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case SummaryCommand.COMMAND_WORD:
        case SummaryCommand.COMMAND_ALIAS:
            return new SummaryCommand();
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case VenueCommand.COMMAND_WORD:
        case VenueCommand.COMMAND_ALIAS:
            return new VenueCommandParser().parse(arguments);
```
###### \java\seedu\address\model\ModelManager.java
``` java
    @Override
    public void deleteModule(Module module) throws DuplicatePersonException, PersonNotFoundException {
        for (int i = 0; i < addressBook.getPersonList().size(); i++) {
            ReadOnlyPerson oldPerson = addressBook.getPersonList().get(i);

            Person newPerson = new Person(oldPerson);
            Set<Module> newModules = newPerson.getModules();
            newModules.remove(module);
            newPerson.setModules(newModules);

            addressBook.updatePerson(oldPerson, newPerson);
        }
    }
```
###### \java\seedu\address\model\person\Address.java
``` java
    public String getBrowserAddress() {
        return value;
    }

}
```
###### \java\seedu\address\model\person\Birthday.java
``` java
    public String getBrowserValue() {
        return browserValue;
    }
```
###### \java\seedu\address\model\person\Name.java
``` java
    public String getBrowserName() {
        return browserName;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
```
###### \java\seedu\address\model\person\Person.java
``` java
    /**
     * Returns a String consisting of all the person's phone numbers separated by commas, for display in the browser.
     */
    @Override
    public String getBrowserPhones() {
        Set<Phone> phones = getPhones();
        String allPhones = "";

        for (Phone phone : phones) {
            allPhones = allPhones.concat(phone.toString());
            allPhones = allPhones.concat(", ");
        }
        if (allPhones.length() == 0) {
            return "";
        }
        allPhones = allPhones.substring(0, allPhones.length() - 2);

        return allPhones;
    }

```
###### \java\seedu\address\model\person\Person.java
``` java
    /**
     * Returns a String consisting of all the person's emails separated by commas, for display in the browser.
     */
    @Override
    public String getBrowserEmails() {
        Set<Email> emails = getEmails();
        String allEmails = "";

        for (Email email : emails) {
            allEmails = allEmails.concat(email.toString());
            allEmails = allEmails.concat(", ");
        }
        if (allEmails.length() == 0) {
            return "";
        }
        allEmails = allEmails.substring(0, allEmails.length() - 2);

        return allEmails;
    }

    public ObjectProperty<EmailList> emailProperty() {
        return emails;
    }

```
###### \java\seedu\address\model\person\Person.java
``` java
    /**
     * Returns a String consisting of all the person's modules separated by commas, for display in the browser.
     */
    @Override
    public String getBrowserModules() {
        Set<Module> modules = getModules();
        String allModules = "";

        for (Module module : modules) {
            allModules = allModules.concat(module.toString());
            allModules = allModules.concat(", ");
        }
        if (allModules.length() == 0) {
            return "";
        }
        allModules = allModules.substring(0, allModules.length() - 2);

        return allModules;
    }

    public ObjectProperty<UniqueModuleList> moduleProperty() {
        return modules;
    }

    /**
     * Replaces this person's modules with the modules in the argument module set.
     */
    public void setModules(Set<Module> replacement) {
        modules.set(new UniqueModuleList(replacement));
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
        return Objects.hash(name, phones, birthday, emails, address, modules);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
```
###### \java\seedu\address\model\person\Photo.java
``` java
    public String getBrowserPhoto() {
        return value;
    }
```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
    @Subscribe
    private void handleShowSummaryRequestEvent (ShowSummaryRequestEvent event) {
        loadDefaultPage();
    }
```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
        String address = p.getAddress().getBrowserAddress();
        String birthday = p.getBirthday().getBrowserValue();
        String name = p.getName().getBrowserName();
        String photo = p.getPhoto().getBrowserPhoto();
        String emails = p.getBrowserEmails();
        String phones = p.getBrowserPhones();
        String modules = p.getBrowserModules();
```
###### \java\seedu\address\ui\BrowserPanel.java
``` java
                panel.executeScript("document.setBirthday(\"" + birthday + "\")");
                panel.executeScript("document.setName(\"" + name + "\")");
                panel.executeScript("document.setAddress(\"" + address + "\")");
                panel.executeScript("document.setPhoto(\"" + photo + "\")");
                panel.executeScript("document.setEmail(\"" + emails + "\")");
                panel.executeScript("document.setPhone(\"" + phones + "\")");
                panel.executeScript("document.setModule(\"" + modules + "\")");
            }
        });
```
###### \java\seedu\address\ui\BusWindow.java
``` java
package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FxViewUtil;

/**
 * Controller for bus page
 */
public class BusWindow extends UiPart<Region> {


    private static final Logger logger = LogsCenter.getLogger(BusWindow.class);
    private static final String ICON = "/images/bus_icon.png";
    private static final String FXML = "BusWindow.fxml";
    private static final String TITLE = "Bus Routes";

    private final Stage dialogStage;

    public BusWindow() {
        super(FXML);
        Scene scene = new Scene(getRoot());
        //Null passed as the parent stage to make it non-modal.
        dialogStage = createDialogStage(TITLE, null, scene);
        dialogStage.setMaximized(false);
        FxViewUtil.setStageIcon(dialogStage, ICON);
    }

    /**
     * Shows the bus window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing bus routes for NUS.");
        dialogStage.showAndWait();
    }
}
```
###### \java\seedu\address\ui\MainWindow.java
``` java
    /**
     * Opens the bus window.
     */
    @FXML
    public void handleBus() {
        BusWindow busWindow = new BusWindow();
        busWindow.show();
    }

    /**
     * Opens the map window.
     */
    @FXML
    public void handleMap() {
        MapWindow mapWindow = new MapWindow();
        mapWindow.show();
    }

    /**
     * Opens the module info in browser.
     */
    @FXML
    public void handleModule(String module) {
        browserPanel.loadPage("https://nusmods.com/modules/" + module);
    }

    /**
     * Opens the venue info in browser.
     */
    @FXML
    public void handleVenue(String venue) {
        browserPanel.loadPage("https://nusmods.com/venues/" + venue);
    }
```
###### \java\seedu\address\ui\MainWindow.java
``` java
    @Subscribe
    private void handleShowBusEvent(ShowBusRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleBus();
    }

    @Subscribe
    private void handleShowMapEvent(ShowMapRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleMap();
    }

    @Subscribe
    private void handleShowModuleEvent(GetModuleRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleModule(event.module);
    }

    @Subscribe
    private void handleShowVenueEvent(VenueRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        handleVenue(event.venue);
    }

```
###### \java\seedu\address\ui\MapWindow.java
``` java
package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FxViewUtil;

/**
 * Controller for map page
 */
public class MapWindow extends UiPart<Region> {


    private static final Logger logger = LogsCenter.getLogger(MapWindow.class);
    private static final String ICON = "/images/map_icon.png";
    private static final String FXML = "MapWindow.fxml";
    private static final String TITLE = "NUS Map";

    private final Stage dialogStage;

    MapWindow() {
        super(FXML);
        Scene scene = new Scene(getRoot());
        //Null passed as the parent stage to make it non-modal.
        dialogStage = createDialogStage(TITLE, null, scene);
        dialogStage.setMaximized(true);
        FxViewUtil.setStageIcon(dialogStage, ICON);
    }

    /**
     * Shows the map window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing map for NUS.");
        dialogStage.showAndWait();
    }
}
```
###### \java\seedu\address\ui\PersonListPanel.java
``` java
    /**
     * Scrolls to the top and clears person selection.
     */
    private void scrollToAndClear() {
        Platform.runLater(() -> {
            personListView.scrollTo(1);
            personListView.getSelectionModel().clearSelection();
        });
    }

    @Subscribe
    private void handleClearPersonListRequestEvent(ClearPersonSelectionEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollToAndClear();
    }
```
###### \java\seedu\address\ui\ResultDisplay.java
``` java
    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        Platform.runLater(() -> displayed.setValue(event.message));

        if (event.isError) {
            setStyleToIndicateCommandFailure();
        } else {
            setStyleToDefault();
        }
    }

    /**
     * Sets the (@code ResultDisplay) to use the default style.
     */
    private void setStyleToDefault() {
        resultDisplay.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the (@code ResultDisplay) to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = resultDisplay.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

}
```
###### \java\seedu\address\ui\StatusBarFooter.java
``` java
    private void setTotalPersons(int totalPersons) {
        this.totalPersons.setText(totalPersons + " person(s) total");
    }
```
###### \resources\view\BusWindow.fxml
``` fxml

<?import javafx.scene.control.ScrollPane?>
<?import javafx.scene.image.Image?>
<?import javafx.scene.image.ImageView?>
<ScrollPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/8.0.111">
   <content>
      <ImageView fitHeight="1000.0" fitWidth="1000.0" pickOnBounds="true" preserveRatio="true">
         <image>
            <Image url="@../docs/images/bus.jpg" />
         </image>
      </ImageView>
   </content>
</ScrollPane>
```
###### \resources\view\DarkThemeCommands.css
``` css
.background {
    -fx-background-color: derive(#1d1d1d, 20%);
    background-color: #383838; /* Used in the default.html file */
    font-family: "Segoe UI";
    color: white;
}

.command {
    font-weight: bold;
}

h1 {
    text-align: center;
    font-size: 32pt;
    font-family: "Segoe UI";
    color: white;
}

li {
    margin: 10px;
}

```
###### \resources\view\default.html
``` html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="DarkThemeCommands.css">
</head>

<body class="background">
<!-- <img src="https://i.imgur.com/BduVh7O.png" draggable="false" alt="ContactHub Logo"> -->
<h1>Command Summary</h1><br>
<ul>
    <li><span class="command">add (a)</span>: Adds a contact to ContactHub.<br>
        <span>Format</span>: add n/NAME p/PHONE... e/EMAIL... a/ADDRESS b/BIRTHDAY [m/MODULE]...<br></li>
    <li><span class="command">edit (e)</span>: Edits the details of the contact identified by the index number used in the last contact listing. Existing values will be overwritten by the input values.<br>
        <span>Format</span>: edit INDEX [n/NAME] [p/PHONE]... [e/EMAIL]... [a/ADDRESS] [b/BIRTHDAY] [m/MODULE]...<br></li>
    <li><span class="command">find (f)</span>: Finds contacts whose names contain any of the given keywords.<br>
        <span>Format</span>: find KEYWORD [MORE_KEYWORDS]...<br></li>
    <li><span class="command">findmodule (fm)</span>: Finds modules whose names contain any of the given keywords.<br>
        <span>Format</span>: findmodule MODULE_NAME [MORE_MODULE_NAMES]...<br></li>
    <li><span class="command">getmodule (gm)</span>: Shows NUSMods module information in browser.<br>
        <span>Format</span>: getmodule MODULE_NAME<br></li>
    <li><span class="command">listmodules (lm)</span>: Shows a list of all modules in ContactHub.<br></li>
    <li><span class="command">delete (d)</span>: Deletes the specified contact from ContactHub.<br>
        <span>Format</span>: delete INDEX<br></li>
    <li><span class="command">select (sl)</span>: Selects the contact identified by the index number used in the last contact listing.<br>
        <span>Format</span>: select INDEX<br></li>
    <li><span class="command">addphoto (ap)</span>: Adds a photo to a contact using the index of the contact in the latest listing.<br>
        <span>Format</span>: addphoto INDEX u/IMAGE_URL<br></li>
    <li><span class="command">addtask (at)</span>: Adds a task to the task list.<br>
        <span>Format</span>: addtask t/APPOINTMENT d/DATE s/START_TIME<br></li>
    <li><span class="command">edittask (et)</span>: Edits the details of the task identified by the index number.<br>
        <span>Format</span>: edittask INDEX [t/APPOINTMENT] [d/DATE] [s/STARTTIME]<br></li>
    <li><span class="command">deletetask (dt)</span>: Deletes the task identified by the index number in the task list.<br>
        <span>Format</span>: deletetask INDEX<br></li>
    <li><span class="command">summary (sm)</span>: Opens the ContactHub Command Summary.<br></li>
    <li><span class="command">venue (v)</span>: Shows NUSMods venue information.<br></li>
    <li><span class="command">bus (b)</span>: Opens the NUS bus routes.<br></li>
    <li><span class="command">map (m)</span>: Opens the NUS map.<br></li>
    <li><span class="command">help (hp)</span>: Opens the User Guide.<br></li>
    <li><span class="command">history (h)</span>: Shows the commands that were previously entered.<br></li>
    <li><span class="command">list (l)</span>: Shows a list of all contacts.<br></li>
    <li><span class="command">clear (c)</span>: Clears ALL contact entries.<br></li>
    <li><span class="command">undo (u)</span>: Restores ContactHub to the state before the previous undoable command was executed.<br></li>
    <li><span class="command">redo (r)</span>: Reverses the most recent undo command.<br></li>
    <li><span class="command">exit (x)</span>: Exits the program.<br></li>
</ul>
<br>
<h1>Keyboard Shortcuts</h1>
<br>
<ul>
    <li><span class="command">Escape</span>: Clears the whole command box.<br>
    <li><span class="command">Alt</span>: Relocates text cursor to the right completely.<br>
    <li><span class="command">Delete</span>: Deletes a chunk of words or chunk of whitespace on the left of the text cursor.<br>
    <li><span class="command">Insert</span>: Fills the command box with the AddCommand parameters.<br>
</ul>

</body>
</html>

```
###### \resources\view\MapWindow.fxml
``` fxml

<?import javafx.scene.control.ScrollPane?>
<?import javafx.scene.image.Image?>
<?import javafx.scene.image.ImageView?>
<ScrollPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity"
            xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/8.0.111">
   <ImageView fitHeight="2600.0" fitWidth="2600.0" pickOnBounds="true" preserveRatio="true">
      <image>
         <Image url="@../docs/images/map.jpg"/>
      </image>
   </ImageView>
</ScrollPane>
```
###### \resources\view\PersonBrowserPanel.html
``` html
        document.setName = function setName(name) {
            document.getElementById("name").innerHTML = name;
        }

        document.setBirthday = function setBirthday(birthday) {
            document.getElementById("birthday").innerHTML = "Birthday: " + birthday;
        }

        document.setEmail = function setEmail(email) {
            document.getElementById("email").innerHTML = "Email(s): " + email;
        }

        document.setPhone = function setPhone(phone) {
            document.getElementById("phone").innerHTML = "Phone Number(s): " + phone;
        }

        document.setModule = function setModule(module) {
            document.getElementById("module").innerHTML = "Module(s): " + module;
        }

        document.setAddress = function setAddress(address) {
            document.getElementById("address").innerHTML = "Address: " + address;
        }

        document.setPhoto = function setPhoto(photo) {
            if (photo === "images/defaultPhoto.png") {
                alert("No custom image loaded");
                document.getElementById("photo").src = "https://t3.ftcdn.net/jpg/00/64/67/52/240_F_64675209_7ve2XQANuzuHjMZXP3aIYIpsDKEbF5dD.jpg";
            } else {
            document.getElementById("photo").src = photo;
            }
        }
    }
</script>

```
###### \resources\view\PersonBrowserPanel.html
``` html
<!-- First Grid: Picture & Contact Info -->
<div class="w3-row">
    <div class="w3-half w3-blue-grey w3-container w3-center" style="height:700px">
        <div class="w3-padding-64 w3-center">
            <h1 id="name"></h1>
        </div>
        <!--<div class="w3-padding-64 w3-center">-->
            <img src="https://t3.ftcdn.net/jpg/00/64/67/52/240_F_64675209_7ve2XQANuzuHjMZXP3aIYIpsDKEbF5dD.jpg" id="photo" class="w3-margin w3-circle" alt="Person" style="width:50%">
        <!--</div>-->
    </div>
    <div class="w3-half w3-black w3-container w3-center" style="height:700px">
        <div class="w3-padding-64">
            <h1>Contact Information</h1>
        </div>
        <!--<div class="w3-padding-64">-->
            <strong>
                <p id="phone"></p>
                <p id="address"></p>
                <p id="email"></p>
                <p id="birthday"></p>
                <p id="module"></p>
                <p id="remark"></p>
            </strong>
        <!--</div>-->
    </div>

</div>

<!-- Second Grid: Map -->
<div class="w3-row">
    <div class="w3-black w3-container w3-center" style="height:700px" id="map_canvas"></div>
</div>

</body>
</html>
```
###### \resources\view\PersonListPanel.fxml
``` fxml
  <ListView fx:id="personListView" style="-fx-background-color: #383838;" VBox.vgrow="ALWAYS" />
</VBox>
```
###### \resources\view\StatusBarFooter.fxml
``` fxml
  <StatusBar styleClass="anchor-pane" fx:id="totalPersons" GridPane.columnIndex="1" />
```
