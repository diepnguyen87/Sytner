package searchPage;

import commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchPage_002_MainContent_SearchResults extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        searchPage = homePage.clickSearchButton();
        searchURL = appURL.concat("search");
    }

    @Test
    public void SearchResult_001_Search_Default() {
        //First Time
        Assert.assertEquals(searchPage.getCurrentSearchResultList(), 20);

        //Maximum 1000 item is showed
        Assert.assertEquals(searchPage.getSearchResultAll(), 1000);
    }

    private SearchPage searchPage;
    private String searchURL;

    private String parentBrand = "Search";
    private String childBrand = "Search";
    private String[] categories = {"Make", "Model", "Model Variant", "New & Used", "Price", "Location", "Fuel Type", "Electric Vehicle Range", "ULEZ Compliance", "Gearbox", "Year & Mileage", "Colour", "Lifestyle", "Performance", "Efficiency", "Advanced"};
}
