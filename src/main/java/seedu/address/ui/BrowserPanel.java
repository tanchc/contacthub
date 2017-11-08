package seedu.address.ui;

//import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.ShowSummaryRequestEvent;
import seedu.address.model.person.ReadOnlyPerson;

//import org.apache.commons.io.FileUtils;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String ADDRESS_PAGE = "PersonBrowserPanel.html";
    public static final String GOOGLE_SEARCH_URL_PREFIX = "https://www.google.com.sg/search?safe=off&q=";
    public static final String GOOGLE_SEARCH_URL_SUFFIX = "&cad=h";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    /*private void loadPersonPage(ReadOnlyPerson person) {
        loadPage(GOOGLE_SEARCH_URL_PREFIX + person.getName().fullName.replaceAll(" ", "+")
                + GOOGLE_SEARCH_URL_SUFFIX);
    }*/

    /**
     * Loads the located address page of the user's address.
     */
    private void loadAddressPage(ReadOnlyPerson person) throws IOException {
        /*ClassLoader classLoader = getClass().getClassLoader();
        File locatedAddressFile = new File(classLoader.getResource("view/PersonBrowserPanel.html").getFile());
        File htmlTemplateFile = new File(classLoader.getResource("view/Template.html").getFile());
        resetPage(htmlTemplateFile, locatedAddressFile);
        String htmlString = FileUtils.readFileToString(locatedAddressFile);
        System.out.println(htmlString);
        String title = "New Page";
        int stopIndex = person.getAddress().getGMapsAddress().indexOf(',');
        String address = person.getAddress().getGMapsAddress().substring(0, stopIndex);
        System.out.println(address);
        System.out.println(htmlString);
        htmlString = htmlString.replace("$body", address.replace(" ", "+"));
        System.out.println(htmlString);
        FileUtils.writeStringToFile(locatedAddressFile, htmlString);*/

        URL addressPage = MainApp.class.getResource(FXML_FILE_FOLDER + ADDRESS_PAGE);
        loadPage(addressPage.toExternalForm());
    }

    /**
     * resets the address page.
     */
    /*private void resetPage(File resetFile, File addressFile) throws IOException {
        String reset = FileUtils.readFileToString(resetFile);
        FileUtils.writeStringToFile(addressFile, reset);
    }*/
    protected void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    //@@author jshoung
    @Subscribe
    private void handleShowSummaryRequestEvent (ShowSummaryRequestEvent event) {
        loadDefaultPage();
    }
    //@@author

    //@@author tanchc
    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) throws IOException {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        ReadOnlyPerson p = event.getNewSelection().person;
        int stopIndex = p.getAddress().getGMapsAddress().indexOf(',');
        String mapAddress;
        //@@author

        //@@author jshoung
        if (stopIndex < 0) {
            mapAddress = p.getAddress().getGMapsAddress();
        } else {
            mapAddress = p.getAddress().getGMapsAddress().substring(0, stopIndex);
        }

        String address = p.getAddress().getBrowserAddress();
        String birthday = p.getBirthday().getBrowserValue();
        String name = p.getName().getBrowserName();
        String photo = p.getPhoto().getBrowserPhoto();
        String emails = p.getBrowserEmails();
        String phones = p.getBrowserPhones();
        String modules = p.getBrowserModules();

        browser.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                WebEngine panel = browser.getEngine();
                panel.executeScript("document.goToLocation(\"" + mapAddress + "\")");
                panel.executeScript("document.setBirthday(\"" + birthday + "\")");
                panel.executeScript("document.setName(\"" + name + "\")");
                panel.executeScript("document.setAddress(\"" + address + "\")");
                panel.executeScript("document.setPhoto(\"" + photo + "\")");
                panel.executeScript("document.setEmail(\"" + emails + "\")");
                panel.executeScript("document.setPhone(\"" + phones + "\")");
                panel.executeScript("document.setModule(\"" + modules + "\")");
            }
        });

        loadAddressPage(event.getNewSelection().person);
    }
    //@@author
}
