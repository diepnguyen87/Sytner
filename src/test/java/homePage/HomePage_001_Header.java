package homePage;

import commons.BaseTest;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPage;
import utilities.model.Brand;
import utilities.model.MenuItems;
import utilities.model.Offer;

import java.util.Arrays;

public class HomePage_001_Header extends BaseTest {

    @Test
    public void SytnerHeader_001_Elements_Visibility() {
        Assert.assertTrue(homePage.isSytnerLogoDisplayed());
        Assert.assertTrue(homePage.isOpenMenuIconDisplayed("Open Menu"));

        Assert.assertTrue(homePage.isSearchIconUndisplayed());
        Assert.assertTrue(homePage.isLocationIconUndisplayed());

        Assert.assertTrue(homePage.isSytnerHeaderCentered());
    }

    @Test
    public void SytnerHeader_002_Elements_Visibility_When_HomePage_Scroll() {
        homePage.moveToNavigation();

        Assert.assertTrue(homePage.isSytnerLogoDisplayed());
        Assert.assertTrue(homePage.isSearchIconDisplayed());
        Assert.assertTrue(homePage.isLocationIconDisplayed());
        Assert.assertTrue(homePage.isOpenMenuIconDisplayed("Open Menu"));

        Assert.assertTrue(homePage.isSytnerHeaderUncentered());
    }

    @Test
    public void SytnerHeader_003_Sytner_Logo() {
        homePage.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
    }

    @Test
    public void SytnerHeader_004_Open_Search_Components() {
        homePage.moveToNavigation();

        Assert.assertTrue(homePage.isSearchInputIconUndisplayed());
        Assert.assertTrue(homePage.isSearchTextboxUndisplayed());
        Assert.assertTrue(homePage.isSearchButtonUndisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconUndisplayed());

        homePage.openSearchComponent();
        Assert.assertTrue(homePage.isSearchIconUndisplayed());
        Assert.assertTrue(homePage.isLocationIconUndisplayed());

        Assert.assertTrue(homePage.isSearchInputIconDisplayed());
        Assert.assertTrue(homePage.isSearchTextboxDisplayed());
        Assert.assertEquals(homePage.getSearchPlaceHolder(), "Search for your next vehicle");
        Assert.assertTrue(homePage.isSearchButtonDisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconDisplayed());
    }

    @Test
    public void SytnerHeader_005_Close_Search_Components() {
        homePage.inputSearchTextbox("search value");

        homePage.closeSearchComponent();

        Assert.assertTrue(homePage.isSearchInputIconUndisplayed());
        Assert.assertTrue(homePage.isSearchTextboxUndisplayed());
        Assert.assertTrue(homePage.isSearchButtonUndisplayed());
        Assert.assertTrue(homePage.isSearchCloseIconUndisplayed());

        Assert.assertTrue(homePage.isSearchIconDisplayed());
        Assert.assertTrue(homePage.isLocationIconDisplayed());

        homePage.openSearchComponent();
        Assert.assertEquals(homePage.getSearchValue(), "");
    }

    @Test
    public void SytnerHeader_006_Search() {
        String keyword = "Ferrari 812";
        homePage.inputSearchTextbox(keyword);
        searchPage = homePage.clickSearchBtn();

        Assert.assertTrue(searchPage.getCurrentURL().contains("https://www.sytner.co.uk/search?"));
        Assert.assertTrue(searchPage.isFilterByKeywordDisplayed(keyword));
        Assert.assertEquals(searchPage.getSearchValue(), keyword);
    }

    @Test
    public void SytnerHeader_007_OpenMenuItems() {
        homePage.openMenuItems();
        Assert.assertTrue(homePage.isMenuItemPopupDisplayed());
    }

    @Test
    public void SytnerHeader_008_CloseMenuItems() {
        homePage.closeMenuItems();
        Assert.assertTrue(homePage.isMenuItemPopupUndisplayed());
    }

    @Test
    public void SytnerHeader_009_MenuItems_Sytner_Logo() {
        homePage.openMenuItems();
        homePage.clickSytnerIcon();
        Assert.assertEquals(homePage.getCurrentURL(), appURL);
        Assert.assertTrue(homePage.isMenuItemPopupUndisplayed());
    }

    @Test(dataProvider = "menuItems", dataProviderClass = DataController.class)
    public void SytnerHeader_010_MenuItems_By_Sections(MenuItems menuItems) {
        String sectionName = menuItems.getSectionName();
        MenuItems.Item[] items = menuItems.getItems();
        Arrays.stream(items).forEach(item -> {
            homePage.openMenuItems();
            Assert.assertTrue(homePage.isSectionMenuItemOpennedOnCurrentPage(sectionName, item.getName()));

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
    public void SytnerHeader_011_CarBrands(Brand brand) {
        homePage.openMenuItems();
        Assert.assertTrue(homePage.isBrandMenuItemOpennedOnCurrentPage(brand.getName()));

        homePage.clickBrandByName(brand.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + brand.getSlug());
        homePage.navigateToURL(appURL);
    }

    @Test(dataProvider = "offers", dataProviderClass = DataController.class)
    public void SytnerHeader_012_Offers(Offer offer) {
        homePage.openMenuItems();
        homePage.selectTabByName("Offers");
        Assert.assertTrue(homePage.isOfferMenuItemOpennedOnCurrentPage(offer.getName()));

        homePage.clickOfferByName(offer.getName());
        Assert.assertEquals(homePage.getCurrentURL(), appURL + offer.getSlug());
        homePage.navigateToURL(appURL);
    }
    private SearchPage searchPage;
}
