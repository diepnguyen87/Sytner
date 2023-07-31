package homePage;

import commons.BaseTest;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPageObject;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class Header_001 extends BaseTest {

    @Test
    public void Header_001_OpenCloseMenuItems() {
        homePage.openMenuItems();
        Assert.assertTrue(homePage.isMenuItemPopupDisplayed());

        homePage.closeMenuItems();
        Assert.assertTrue(homePage.isMenuItemPopupUndisplayed());
    }

    @Test
    public void Header_002_Sytner_Logo() {
        homePage.clickSytnerIcon();
        Assert.assertEquals(driver.getCurrentUrl(), appURL);

        homePage.openMenuItems();
        homePage.clickSytnerIcon();
        Assert.assertEquals(driver.getCurrentUrl(), appURL);
        Assert.assertTrue(homePage.isMenuItemPopupUndisplayed());
    }

    @Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void Header_003_MenuItems(MenuItems menuItems) {
        String sectionName = menuItems.getSectionName();
        MenuItems.Item[] items = menuItems.getItems();
        Arrays.stream(items).forEach(item -> {
            homePage.openMenuItems();
            homePage.clickMenuItemsBySectionAndName(sectionName, item.getName());

            if (item.getName().equalsIgnoreCase("Careers")) {
                Assert.assertEquals(homePage.getCurrentURL(), item.getSlug());
            } else {
                Assert.assertEquals(homePage.getCurrentURL(), appURL + item.getSlug());
            }
            homePage.navigateToURL(appURL);
        });
    }

    @Test(dataProvider = "carBrands", dataProviderClass = DataController.class)
    public void Header_004_CarBrands(Brand brand) {
        homePage.openMenuItems();
        homePage.clickBrandByName(brand.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(appURL);
    }

    @Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void Header_005_Offers(Offer offer) {
        homePage.openMenuItems();
        homePage.selectTabByName("Offers");
        homePage.clickOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(appURL);
    }

    @Test
    public void Header_006_Search_Location_Icon_Visibility() {
        Assert.assertTrue(homePage.isSearchIconUndisplayed());
        Assert.assertTrue(homePage.isLocationIconUndisplayed());
        Assert.assertTrue(homePage.isNavbarHeaderCentered());

        homePage.moveToNavigation();
        Assert.assertTrue(homePage.isSearchIconDisplayed());
        Assert.assertTrue(homePage.isLocationIconDisplayed());
        Assert.assertTrue(homePage.isNavbarHeaderUncentered());
    }

    @Test
    public void Header_007_Search_Input_Components_Visibility() {
        Assert.assertTrue(homePage.isSearchInputIconUndisplayed());
        Assert.assertTrue(homePage.isSearchTextboxUndisplayed());
        Assert.assertTrue(homePage.isSearchButtonUndisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconUndisplayed());

        homePage.clickSearchIcon();
        Assert.assertTrue(homePage.isSearchIconUndisplayed());
        Assert.assertTrue(homePage.isLocationIconUndisplayed());
        Assert.assertTrue(homePage.isSearchInputIconDisplayed());
        Assert.assertTrue(homePage.isSearchTextboxDisplayed());
        Assert.assertEquals(homePage.getSearchPlaceHolder(), "Search for your next vehicle");
        Assert.assertTrue(homePage.isSearchButtonDisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconDisplayed());
    }

    @Test
    public void Header_008_Close_Open_Search_Input() {
        homePage.inputSearchTextbox("search value");
        homePage.closeSearchIcon();
        Assert.assertTrue(homePage.isSearchInputIconUndisplayed());
        Assert.assertTrue(homePage.isSearchTextboxUndisplayed());
        Assert.assertTrue(homePage.isSearchButtonUndisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconUndisplayed());
        Assert.assertTrue(homePage.isSearchIconDisplayed());
        Assert.assertTrue(homePage.isLocationIconDisplayed());

        homePage.clickSearchIcon();
        Assert.assertEquals(homePage.getSearchValue(), "");
    }

    @Test
    public void Header_009_Search() {
        String keyword = "Ferrari 812";
        homePage.inputSearchTextbox(keyword);
        searchPage = homePage.clickSearchBtn();

        Assert.assertTrue(searchPage.getCurrentURL().contains("https://www.sytner.co.uk/search?"));
        Assert.assertTrue(searchPage.isFilterByKeywordDisplayed(keyword));
        Assert.assertEquals(searchPage.getSearchValue(), keyword);
    }

    private SearchPageObject searchPage;
}
