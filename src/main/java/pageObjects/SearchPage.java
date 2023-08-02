package pageObjects;

import UI.page.SearchPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFilterByKeywordDisplayed(String keyword) {
        waitForElementVisible(SearchPageUI.SEARCH_FILTER_SET, keyword);
        return isElementDisplayedInDOM(SearchPageUI.SEARCH_FILTER_SET, keyword);
    }

    public String getSearchValue() {
        waitForElementVisible(SearchPageUI.SEARCH_TEXTBOX);
        return getAttributeValue(SearchPageUI.SEARCH_TEXTBOX, "value");
    }
}
