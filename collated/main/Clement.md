# Clement
###### /java/seedu/address/ui/BrowserPanel.java
``` java
    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) throws IOException {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        ReadOnlyPerson p = event.getNewSelection().person;
        int stopIndex = p.getAddress().getGMapsAddress().indexOf(',');
        String mapAddress = p.getAddress().getGMapsAddress().substring(0, stopIndex);
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
}
```
