package pages;

import UI.page.SearchPageUI;
import commons.BasePage;
import commons.GlobalContants;
import data.DataController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DataObjectBuilder;
import utilities.model.CarMake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
        verticalCardPriceList = new ArrayList<>();
    }

    public boolean isFilterByKeywordDisplayed(String keyword) {
        waitForElementVisible(SearchPageUI.SEARCH_FILTER_SET, keyword);
        return isElementDisplayedInDOM(SearchPageUI.SEARCH_FILTER_SET, keyword);
    }

    public String getSearchValue() {
        waitForElementVisible(SearchPageUI.SEARCH_TEXTBOX);
        return getAttributeValue(SearchPageUI.SEARCH_TEXTBOX, "value");
    }

    public void moveToBottomPage() {
        scrollToBottomPage();
    }

    public String getSearchFiltersTitle() {
        waitForElementVisible(SearchPageUI.SEARCH_FILTERS_TITLE);
        return getElementText(SearchPageUI.SEARCH_FILTERS_TITLE);
    }

    public boolean isFilterByCategoryDisplayed(String category) {
        waitForElementVisible(SearchPageUI.SEARCH_FILTER_BY_DYNAMIC_CATEGORY_NAME, category);
        return isElementDisplayedInDOM(SearchPageUI.SEARCH_FILTER_BY_DYNAMIC_CATEGORY_NAME, category);
    }

    public boolean isCategoryExpanded(String category) {
        Boolean isExpanded = Boolean.parseBoolean(getAttributeValue(SearchPageUI.SEARCH_FILTER_BY_DYNAMIC_CATEGORY_NAME, "aria-expanded", category));
        if (isExpanded) {
            return true;
        }
        return false;
    }

    public boolean isSearchMakeTextboxDisplayedWithPlaceHolder(String expectedSearchPlaceHolder) {
        waitForElementVisible(SearchPageUI.SEARCH_MAKE_TEXTBOX);
        if (isElementDisplayedInDOM(SearchPageUI.SEARCH_MAKE_TEXTBOX)) {
            String actualPlaceHolder = getAttributeValue(SearchPageUI.SEARCH_MAKE_TEXTBOX, "placeholder");
            if (actualPlaceHolder.equals(expectedSearchPlaceHolder)) {
                return true;
            }
        }
        return false;
    }

    public boolean isShowMoreDisplayed() {
        waitForElementVisible(SearchPageUI.MAKE_SHOW_MORE_BUTTON);
        return isElementDisplayedInDOM(SearchPageUI.MAKE_SHOW_MORE_BUTTON);
    }

    public boolean isShowLessDisplayed() {
        waitForElementVisible(SearchPageUI.MAKE_SHOW_LESS_BUTTON);
        return isElementDisplayedInDOM(SearchPageUI.MAKE_SHOW_LESS_BUTTON);
    }

    public void clickShowMoreButton() {
        sleepInSecond(1);
        waitForElementVisible(SearchPageUI.MAKE_SHOW_MORE_BUTTON);
        clickToElement(SearchPageUI.MAKE_SHOW_MORE_BUTTON);
    }

    public void clickShowLessButton() {
        sleepInSecond(1);
        waitForElementVisible(SearchPageUI.MAKE_SHOW_LESS_BUTTON);
        clickToElement(SearchPageUI.MAKE_SHOW_LESS_BUTTON);
    }

    public int getCurrentMakeOptionQuantity() {
        waitForElementVisible(SearchPageUI.ALL_MAKE_OPTION);
        return getElementSize(SearchPageUI.ALL_MAKE_OPTION);
    }

    public boolean isMakeOptionDisplayed(String makeName) {
        waitForElementVisible(SearchPageUI.DYNAMIC_MAKE_OPTION_BY_NAME, makeName);
        return isElementDisplayedInDOM(SearchPageUI.DYNAMIC_MAKE_OPTION_BY_NAME, makeName);
    }

    public int getCountByMake(String makeName) {
        makeName = makeName.toLowerCase();
        waitForElementVisible(SearchPageUI.DYNAMIC_MAKE_COUNT, makeName);
        return Integer.parseInt(getElementText(SearchPageUI.DYNAMIC_MAKE_COUNT, makeName));
    }

    public boolean isAllMakeOptionsDisplayed() {
        if (isElementUnDisplayed(SearchPageUI.DYNAMIC_MAKE_NAME, "All")) {
            return false;
        }

        CarMake[] carMakes = DataController.getCarMakeDataSet();
        int expectedCarMakesQuantity = carMakes.length;
        int round = carMakes.length / 20;

        int cumulateMakeOptions = 20;
        int currentIndex = 0;
        for (int i = 0; i < round; i++) {
            if (getElementText(SearchPageUI.MAKE_SHOW_MORE_LESS_BUTTON).equalsIgnoreCase("Show more")) {
                for (int j = currentIndex; j < 20; j++) {
                    String makeName = carMakes[j].getName();
                    int makeCount = carMakes[j].getQuantity();

                    scrollToElementOnDown(SearchPageUI.DYNAMIC_MAKE_OPTION_BY_NAME, makeName);
                    if (isMakeOptionDisplayed(makeName) == false) {
                        return false;
                    }
                    if (getCountByMake(makeName.replace(" ", "")) != makeCount) {
                        return false;
                    }
                    if (j == 19) {
                        currentIndex = j + 1;
                    }
                }
                clickShowMoreButton();
                cumulateMakeOptions += 20;
            } else {
                for (int j = currentIndex; j < expectedCarMakesQuantity; j++) {
                    String makeName = carMakes[j].getName();
                    int makeCount = carMakes[j].getQuantity();

                    scrollToElementOnTop(SearchPageUI.DYNAMIC_MAKE_OPTION_BY_NAME, makeName);
                    if (isMakeOptionDisplayed(makeName) == false) {
                        return false;
                    }
                    if (getCountByMake(makeName.replace(" ", "")) != makeCount) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void loadCarMakeCountFromDB() {
        List<CarMake> carMakes = new ArrayList<>();

        while (true) {
            if (getElementText(SearchPageUI.MAKE_SHOW_MORE_LESS_BUTTON).equalsIgnoreCase("Show more")) {
                scrollToElementOnDown(SearchPageUI.MAKE_SHOW_MORE_BUTTON);
                sleepInSecond(1);
                clickShowMoreButton();
            } else {
                break;
            }
        }

        for (WebElement element : getElements(SearchPageUI.ALL_MAKE_OPTION)) {
            String makeName = element.findElement(By.tagName("div")).getText();
            if (makeName.equalsIgnoreCase("All")) {
                continue;
            }
            int makeCount = Integer.parseInt(element.findElement(By.tagName("span")).getText());
            carMakes.add(new CarMake(makeName, makeCount));
        }

        DataObjectBuilder.buildJsonFile(GlobalContants.TEST_RESOURCES.concat("CarMakes.json"), carMakes);
    }

    public int getSearchResultAll() {
        while (true) {
            if (getElementSize(SearchPageUI.SEARCH_RESULT_SHOW_MORE) == 1) {
                scrollToElementOnDown(SearchPageUI.SEARCH_RESULT_SHOW_MORE);
                sleepInSecond(3);
                clickToElement(SearchPageUI.SEARCH_RESULT_SHOW_MORE);
                sleepInSecond(2);
            } else {
                break;
            }
        }
        for (WebElement element : getElements(SearchPageUI.VERTICAL_CARD_PRICE)) {
            int startIndex = element.getText().indexOf('£') + 1;
            String price = element.getText().substring(startIndex).replace(",", "");
            if (price.contains(" + VAT")) {
                 price = price.replace(" + VAT", "");
            }
            verticalCardPriceList.add(Integer.parseInt(price));
        }
        return getElementSize(SearchPageUI.SEARCH_RESULT_LIST);
    }

    public int getCurrentSearchResultList() {
        waitForElementVisible(SearchPageUI.SEARCH_RESULT_LIST);
        return getElementSize(SearchPageUI.SEARCH_RESULT_LIST);
    }

    public List<Integer> getActualVerticalCardPriceList() {
        if (verticalCardPriceList.size() == 0) {
            getSearchResultAll();
        }
        return verticalCardPriceList;
    }

    private List<Integer> verticalCardPriceList;

    public boolean isPriceSortedByDesc() {
        List<Integer> actualPriceList = getActualVerticalCardPriceList();
        List<Integer> expectedPriceList = actualPriceList.stream().sorted((o1, o2) -> o1.compareTo(02)).collect(Collectors.toList());

        for (int i = 0; i < actualPriceList.size(); i++) {
            int actual = actualPriceList.get(i);
            int expected = expectedPriceList.get(i);
            if (actual != expected) {
                return false;
            }
        }
        return true;
    }
}
