package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String ADDRESS_PAGE = "LocatedAddress.html";
    public static final String GOOGLE_SEARCH_URL_PREFIX = "https://www.google.com.sg/search?safe=off&q=";
    public static final String GOOGLE_SEARCH_URL_SUFFIX = "&cad=h";
    public static final String GOOGLE_MAPS_URL_PREFIX = "https://www.google.com.sg/maps/place/";

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

    private void loadPersonPage(ReadOnlyPerson person) {
        loadPage(GOOGLE_SEARCH_URL_PREFIX + person.getName().fullName.replaceAll(" ", "+")
                + GOOGLE_SEARCH_URL_SUFFIX);
    }

    /**
     * Loads the located address page of the user's address.
     */
    private void loadAddressPage(ReadOnlyPerson person) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File htmlTemplateFile = new File(classLoader.getResource("view/LocatedAddress.html").getFile());
        resetPage(htmlTemplateFile);
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
        System.out.println(htmlString);
        String title = "New Page";
        int stopIndex = person.getAddress().getGMapsAddress().indexOf(',');
        String address = person.getAddress().getGMapsAddress().substring(0, stopIndex);
        System.out.println(address);
        String body = "<div class=" + "\"" + "mapouter" + "\"" + "><div class=" + "\"" + "gmap_canvas" + "\""
                + "><iframe width=" + "\"" + "600" + "\"" + " height=" + "\"" + "500" + "\"" + " id=" + "\""
                + "gmap_canvas" + "\"" + " src= " +  "\"" + "https://maps.google.com/maps?q=" + address
                + "&t=&z=13&ie=UTF8&iwloc=&output=embed" + "\"" + " frameborder=" + "\"" + "0" + "\"" + " scrolling="
                + "\"" + "no" + "\"" + " marginheight=" + "\"" + "0" + "\"" + " marginwidth=" + "\"" + "0" + "\""
                + "></iframe>google maps einbinden <a href=" + "\"" + "http://www.pureblack.de/google-maps/" + "\""
                + ">pureblack.de</a></div><style>.mapouter{overflow:hidden;height:500px;width:600px;}.gmap_canvas "
                + "{background:none!important;height:500px;width:600px;}</style></div>";
        htmlString = htmlString.replace("$body", body);
        System.out.println(htmlString);
        FileUtils.writeStringToFile(htmlTemplateFile, htmlString);
        URL addressPage = MainApp.class.getResource(FXML_FILE_FOLDER + ADDRESS_PAGE);
        loadPage(addressPage.toExternalForm());
    }

    /**
     * resets the address page.
     */
    private void resetPage(File file) throws IOException {
        String reset = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <link rel=\"stylesheet\" href=\"DarkTheme.css\">"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body class = background>\n"
                + "$body\n"
                + "</body>\n"
                + "</html>";
        FileUtils.writeStringToFile(file, reset);
    }

    public void loadPage(String url) {
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

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) throws IOException {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadAddressPage(event.getNewSelection().person);
    }
}
