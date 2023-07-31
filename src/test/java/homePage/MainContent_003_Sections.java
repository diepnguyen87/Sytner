package homePage;

import commons.BasePage;
import commons.BaseTest;
import data.DataController;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SearchPageObject;
import utilities.model.FeaturedNewCarOffers;

public class MainContent_003_Sections extends BaseTest {

    @Test
    public void MainContent_001_FeatureNewCarOffers_Section() {
        String parentWindow = homePage.getWindowHandle();
        homePage.moveToSection("Featured New Car Offers");
        for (FeaturedNewCarOffers car : DataController.featuredNewCarOfferDataSet()) {
            String expectedSubHeading = car.getSubHeading();
            String expectedCurrentURL = appURL.concat(car.getSlug());

            Assert.assertTrue(homePage.isSubHeadingDisplayed(expectedSubHeading));
            basePage = homePage.openSubHeadingOnNewTab(expectedSubHeading);
            Assert.assertEquals(basePage.getnCloseCurrentURL(parentWindow), expectedCurrentURL);

            Assert.assertTrue(homePage.isHeadingDisplayed(expectedSubHeading));
            homePage.openHeadingOnNewTab(expectedSubHeading);
            Assert.assertEquals(basePage.getnCloseCurrentURL(parentWindow), expectedCurrentURL);

            Assert.assertTrue(homePage.isImageDisplayed(expectedSubHeading));
            basePage = homePage.openImageOnNewTab(expectedSubHeading);
            Assert.assertEquals(basePage.getnCloseCurrentURL(parentWindow), expectedCurrentURL);

            for (String offer : car.getOffers()) {
                Assert.assertTrue(homePage.isOfferDisplayed(expectedSubHeading, offer));
                basePage = homePage.openOfferOnNewTab(expectedSubHeading, offer);
                Assert.assertEquals(basePage.getnCloseCurrentURL(parentWindow), expectedCurrentURL);
            }

            Assert.assertTrue(homePage.isOfferLinkDisplayed(expectedSubHeading));
            basePage = homePage.openOfferLinkOnNewTab(expectedSubHeading);
            Assert.assertEquals(basePage.getnCloseCurrentURL(parentWindow), expectedCurrentURL);
        }
    }

    private WebDriver driver;
    private SearchPageObject searchPage;
    private BasePage basePage;
}
