package UI.comp;

public interface SytnerHeaderCompUI {

    String SYTNER_LOGO = "css=img[alt='Sytner Group Logo']";

    String LOCATION_ICON = "css=a[title='Locations']";

    String SEARCH_ICON = "xpath=//button[@title='Open search' and contains(@id, 'desktop')]";

    String SEARCH_TEXTBOX = "xpath=//input[@type='search' and contains(@id, 'search-input-desktop')]";
    String SEARCH_BUTTON = "xpath=//button[text()='Search' and contains(@id, 'search-input-desktop')]";
    String CLOSE_SEARCH_ICON = "xpath=//button[@title='Close search' and contains(@id, 'desktop')]";

    String MENU_ITEMS_OPEN_CLOSE_ICON = "xpath=//div[contains(@class, 'navbar-header')]/button[@title='%s']";
    String DYNAMIC_MENU_ITEM_BY_NAME = "xpath=//ul[@role='tablist']/following-sibling::div//a[text()='%s']";
    String DYNAMIC_SECTION_NAME = "xpath=//h6[text()='%s']";
    String DYNAMIC_MENU_ITEM_BY_SECTION_NAME = "xpath=//h6[text()='%s']/following-sibling::ul//a[text()='%s']";
}
