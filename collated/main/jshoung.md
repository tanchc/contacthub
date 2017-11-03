# jshoung
###### \src\main\java\seedu\address\model\ModelManager.java
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

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return FXCollections.unmodifiableObservableList(filteredTasks);
    }

    @Override
    public void updateFilteredTaskList(Predicate<ReadOnlyTask> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && filteredPersons.equals(other.filteredPersons);
    }

}
```
###### \src\main\java\seedu\address\model\person\Address.java
``` java
    public String getBrowserAddress() {
        return value;
    }

}
```
###### \src\main\java\seedu\address\model\person\Birthday.java
``` java
    public String getBrowserValue() {
        return browserValue;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof Birthday
                && this.value.equals(((Birthday) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
```
###### \src\main\java\seedu\address\model\person\Name.java
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
###### \src\main\java\seedu\address\model\person\Person.java
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

```
###### \src\main\java\seedu\address\model\person\Person.java
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

    /**
     * Replaces this person's emails with the emails in the argument email set.
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
     * Returns an immutable module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules.get().toSet());
    }

```
###### \src\main\java\seedu\address\model\person\Person.java
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
###### \src\main\java\seedu\address\model\person\Photo.java
``` java
    public String getBrowserPhoto() {
        return value;
    }

    /**
     * Returns true if a given string is a valid url.
     */
    public static boolean isValidPhoto(String test) {
        if (test.equals("images/defaultPhoto.png")) {
            return true;
        }

        if (test.matches(PHOTO_VALIDATION_REGEX)) {
            try {
                Image image = ImageIO.read(new URL(test));
                if (image == null) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
            return true;

        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Photo // instanceof handles nulls
                && this.value.equals(((Photo) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
```
###### \src\main\java\seedu\address\ui\ResultDisplay.java
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
###### \src\main\resources\view\DarkThemeCommands.css
``` css
.background {
    -fx-background-color: derive(#1d1d1d, 20%);
    background-color: #383838; /* Used in the default.html file */
    font-family: "Segoe UI";
    color: white;
}

img {
    user-drag: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;ser-select: none;
    -moz-user-select: none;
    -webkit-user-drag: none;
    -webkit-user-select: none;
    -ms-user-select: none;
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

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #555555;
    -fx-opacity: 0.9;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #1d1d1d;
    -fx-background-color: #1d1d1d;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color:
            transparent
            transparent
            derive(-fx-base, 80%)
            transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider {
    -fx-background-color: derive(#1d1d1d, 20%);
    -fx-border-color: transparent transparent transparent #4d4d4d;
}

.split-pane {
    -fx-border-radius: 1;
    -fx-border-width: 1;
    -fx-background-color: derive(#1d1d1d, 20%);
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
}

.list-cell {
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-padding: 0 0 0 0;
}

.list-cell:filled:even {
    -fx-background-color: #3c3e3f;
}

.list-cell:filled:odd {
    -fx-background-color: #515658;
}

.list-cell:filled:selected {
    -fx-background-color: #424d5f;
}

.list-cell:filled:selected #cardPane {
    -fx-border-color: #3e7b91;
    -fx-border-width: 1;
}

.list-cell .label {
    -fx-text-fill: white;
}

.cell_big_label {
    -fx-font-family: "Segoe UI Semibold";
    -fx-font-size: 16px;
    -fx-text-fill: #010504;
}

.cell_small_label {
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13px;
    -fx-text-fill: #010504;
}

.anchor-pane {
    -fx-background-color: derive(#1d1d1d, 20%);
}

.pane-with-border {
    -fx-background-color: derive(#1d1d1d, 20%);
    -fx-border-color: derive(#1d1d1d, 10%);
    -fx-border-top-width: 1px;
}

.status-bar {
    -fx-background-color: derive(#1d1d1d, 20%);
    -fx-text-fill: black;
}

.result-display {
    -fx-background-color: transparent;
    -fx-font-family: "Segoe UI Light";
    -fx-font-size: 13pt;
    -fx-text-fill: white;
}

.result-display .label {
    -fx-text-fill: black !important;
}

.status-bar .label {
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
}

.status-bar-with-border {
    -fx-background-color: derive(#1d1d1d, 30%);
    -fx-border-color: derive(#1d1d1d, 25%);
    -fx-border-width: 1px;
}

.status-bar-with-border .label {
    -fx-text-fill: white;
}

.grid-pane {
    -fx-background-color: derive(#1d1d1d, 30%);
    -fx-border-color: derive(#1d1d1d, 30%);
    -fx-border-width: 1px;
}

.grid-pane .anchor-pane {
    -fx-background-color: derive(#1d1d1d, 30%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d, 50%);
}

.context-menu .label {
    -fx-text-fill: white;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d, 20%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: black;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
    -fx-background-color: white;
    -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: #1d1d1d;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: #1d1d1d;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: white;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: derive(#1d1d1d, 25%);
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: white;
    -fx-text-fill: white;
}

.scroll-bar {
    -fx-background-color: derive(#1d1d1d, 20%);
}

.scroll-bar .thumb {
    -fx-background-color: derive(#1d1d1d, 50%);
    -fx-background-insets: 3;
}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-padding: 0 0 0 0;
}

.scroll-bar .increment-arrow, .scroll-bar .decrement-arrow {
    -fx-shape: " ";
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 1 8 1 8;
}

.scroll-bar:horizontal .increment-arrow, .scroll-bar:horizontal .decrement-arrow {
    -fx-padding: 8 1 8 1;
}

#cardPane {
    -fx-background-color: transparent;
    -fx-border-width: 0;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: #F70D1A;
}

#commandTextField {
    -fx-background-color: transparent #383838 transparent #383838;
    -fx-background-insets: 0;
    -fx-border-color: #383838 #383838 #ffffff #383838;
    -fx-border-insets: 0;
    -fx-border-width: 1;
    -fx-font-family: "Segoe UI Light";
    -fx-font-size: 13pt;
    -fx-text-fill: white;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, black, 10, 0, 0, 0);
}

#resultDisplay .content {
    -fx-background-color: transparent, #383838, transparent, #383838;
    -fx-background-radius: 0;
}

#mods {
    -fx-hgap: 7;
    -fx-vgap: 3;
}

#mods .label {
    -fx-text-fill: white;
    -fx-background-color: #3e7b91;
    -fx-padding: 1 3 1 3;
    -fx-border-radius: 2;
    -fx-background-radius: 2;
    -fx-font-size: 11;
}
```
###### \src\main\resources\view\default.html
``` html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="DarkThemeCommands.css">
</head>

<body class="background">
<!-- <img src="https://i.imgur.com/BduVh7O.png" draggable="false" alt="ContactHub Logo"> -->
<h1>ContactHub Commands</h1>
<ul>
    <li><span class="command">add</span>: Adds a person to the address book.<br>
        <span class="command">Format</span>: add n/NAME p/PHONE e/EMAIL a/ADDRESS [t/MOD]...<br></li>
    <li><span class="command">edit</span>: Edits the details of the person identified by the index number used in the last person listing. Existing values will be overwritten by the input values.<br>
        <span class="command">Format</span>: edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/MOD]...<br></li>
    <li><span class="command">find</span>: Finds persons whose names contain any of the given keywords.<br>
        <span class="command">Format</span>: find KEYWORD [MORE_KEYWORDS]...<br></li>
    <li><span class="command">findmodule</span>: Finds moduless whose names contain any of the given keywords.<br>
        <span class="command">Format</span>: findmod KEYWORD [MORE_KEYWORDS]...<br></li>
    <li><span class="command">listmodules</span>: Shows a list of all modules in the address book.<br></li>
    <li><span class="command">delete</span>: Deletes the specified person from the address book.<br>
        <span class="command">Format</span>: delete INDEX<br></li>
    <li><span class="command">select</span>: Selects the person identified by the index number used in the last person listing.<br>
        <span class="command">Format</span>: select INDEX<br></li>
    <li><span class="command">addphoto</span>: Adds a photo to a person in the address book using the index of the person in the latest listing.<br>
        <span class="command">Format</span>: addphoto INDEX u/IMAGE_URL<br></li>
    <li><span class="command">help</span>: Opens the User Guide.<br></li>
    <li><span class="command">list</span>: Shows a list of all persons in the address book.<br></li>
    <li><span class="command">clear</span>: Clears all entries from the address book.<br></li>
    <li><span class="command">undo</span>: Restores the address book to the state before the previous undoable command was executed.<br></li>
    <li><span class="command">redo</span>: Reverses the most recent undo command.<br></li>
    <li><span class="command">exit</span>: Exits the program.<br></li>
</ul>
</body>
</html>

```
###### \src\main\resources\view\PersonBrowserPanel.html
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
            document.getElementById("phone").innerHTML = "Phone(s): " + phone;
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
###### \src\main\resources\view\PersonBrowserPanel.html
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
###### \src\main\resources\view\PersonListPanel.fxml
``` fxml
  <ListView fx:id="personListView" style="-fx-background-color: #383838;" VBox.vgrow="ALWAYS" />
</VBox>
```
###### \src\main\resources\view\TaskListPanel.fxml
``` fxml
  <ListView fx:id="taskListView" style="-fx-background-color: #383838;" VBox.vgrow="ALWAYS" />
</VBox>
```
###### \src\test\java\seedu\address\logic\commands\AddCommandTest.java
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
