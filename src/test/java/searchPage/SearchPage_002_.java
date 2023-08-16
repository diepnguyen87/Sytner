package searchPage;

import UI.page.SearchPageUI;
import commons.BaseTest;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchPage;
import utilities.model.CarMake;

public class SearchPage_002_ extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        searchPage = homePage.clickSearchButton();
        searchURL = appURL.concat("search");
    }

    @Test
    public void Filters_001_Visibility() {
        String expectedSearchFiltersTitle = "Filters";
        Assert.assertEquals(searchPage.getSearchFiltersTitle(), expectedSearchFiltersTitle);

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];
            if (i == 0) {
                Assert.assertTrue(searchPage.isCategoryExpanded(category));
                continue;
            }
            Assert.assertTrue(searchPage.isFilterByCategoryDisplayed(category));
            Assert.assertFalse(searchPage.isCategoryExpanded(category));
        }
    }

    @Test
    public void Filters_002_Category_Make_Show_More_Less() {
        searchPage.scrollToElementOnDown(SearchPageUI.MAKE_SHOW_MORE_LESS_BUTTON);
        Assert.assertTrue(searchPage.isShowMoreDisplayed());

        searchPage.clickShowMoreButton();
        Assert.assertTrue(searchPage.isShowLessDisplayed());

        CarMake[] carMakes = DataController.getCarMakeDataSet();
        int expectedCarMakesQuantity = carMakes.length;
        Assert.assertEquals(searchPage.getCurrentMakeOptionQuantity()-1, expectedCarMakesQuantity);
    }

    @Test
    public void Filters_003_Category_Make_Visibility() {
        //Create expected data
        searchPage.loadCarMakeCountFromDB();

        Assert.assertTrue(searchPage.isSearchMakeTextboxDisplayedWithPlaceHolder("Search Makes"));
        Assert.assertTrue(searchPage.isAllMakeOptionsDisplayed());
    }

    private SearchPage searchPage;
    private String searchURL;

    private String parentBrand = "Search";
    private String childBrand = "Search";
    private String[] categories = {"Make", "Model", "Model Variant", "New & Used", "Price", "Location", "Fuel Type", "Electric Vehicle Range", "ULEZ Compliance", "Gearbox", "Year & Mileage", "Colour", "Lifestyle", "Performance", "Efficiency", "Advanced"};
}
