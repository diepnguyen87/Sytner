package homePage;

import commons.BaseTest;
import data.DataController;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.SearchPage;
import utilities.model.Article;
import utilities.model.Brand;
import utilities.model.Service;

public class HomePage_002_MainContent_Section_Video extends BaseTest {

    @Test
    public void VideoSection_001_Search(){
        String expectedURL = "https://www.sytner.co.uk/search";
        searchPage = homePage.clickSearchButton();
        Assert.assertTrue(searchPage.getCurrentURL().contains(expectedURL));
        searchPage.navigateToURL(appURL);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test()
    public void VideoSection_002_Jump_Point_OurBrands() {
        Assert.assertTrue(homePage.isJumpPointDisplayed("Our brands"));
        homePage.clickJumpPointByName("Our brands");

        for (Brand brand : DataController.carBrandDataSet()) {
            Assert.assertTrue(homePage.isBrandNameDisplayed(brand.getName()));
        }
    }

    @Test()
    public void VideoSection_003_Jump_Point_SellYourCar() {
        Assert.assertTrue(homePage.isJumpPointDisplayed("Sell your car"));
        homePage.clickJumpPointByName("Sell your car");

        for (Article article : DataController.articleDataSet()) {
            String articleTitle = article.getTitle();
            Assert.assertTrue(homePage.isArticleTitleDisplayed(articleTitle));
            Assert.assertEquals(homePage.getArticleContent(articleTitle), article.getContent());
            Assert.assertTrue(homePage.isArticleBtnDisplayed(articleTitle, article.getFirstBtn()));
            Assert.assertTrue(homePage.isArticleBtnDisplayed(articleTitle, article.getSecondBtn()));
        }
    }

    @Test()
    public void VideoSection_004_Jump_Point_OurDealerships() {
        Assert.assertTrue(homePage.isJumpPointDisplayed("Our dealerships"));
        homePage.clickJumpPointByName("Our dealerships");

        Assert.assertTrue(homePage.isDealershipTitleDisplayed());
        Assert.assertTrue(homePage.isPostCodeSearchTextboxDisplayed());
        Assert.assertEquals(homePage.getPlaceHolderPostCodeSearchTextbox(), "Sort by postcode or location");
        Assert.assertEquals(homePage.getPlaceHolderBrandTextbox(), "Select a brand");

        homePage.clickBrandListbox();
        Assert.assertTrue(homePage.isAllBrandsPresence(DataController.brandDataSet()));
        Assert.assertTrue(homePage.isFindADealershipButtonDisplayed());
    }

    @Test
    public void VideoSection_005_Jump_Point_Service() {
        Assert.assertTrue(homePage.isJumpPointDisplayed("Service, MOT & Repairs"));
        homePage.clickJumpPointByName("Service, MOT & Repairs");

        for (Service service : DataController.serviceDataSet()) {
            String serviceTitle = service.getTitle();
            Assert.assertTrue(homePage.isServiceTitleDisplayed(serviceTitle));
            Assert.assertEquals(homePage.getServiceContent(serviceTitle), service.getContent());
            Assert.assertTrue(homePage.isServiceButtonDisplayed(serviceTitle, service.getBtn()));
            Assert.assertEquals(homePage.getServiceButtonURL(serviceTitle, service.getBtn()), appURL + service.getSlug());
        }
    }

    private WebDriver driver;
    private SearchPage searchPage;
}
