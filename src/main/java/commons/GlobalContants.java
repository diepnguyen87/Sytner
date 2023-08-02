package commons;

import java.io.File;
import java.time.Duration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"file:src/main/java/commons/GlobalContanst.properties"})
public interface GlobalContants extends Config {

    /* System info */
    String FILE_SEPARATOR = File.separator;
    String PROJECT_PATH = System.getProperty("user.dir");
    String TEST_RESOURCES = PROJECT_PATH.concat(FILE_SEPARATOR)
            .concat("src").concat(FILE_SEPARATOR)
            .concat("test").concat(FILE_SEPARATOR)
            .concat("resources").concat(FILE_SEPARATOR);

    String OS_NAME = System.getProperty("os.name");
    String JAVA_VERSION = System.getProperty("java.version");

    /* App info */
    GlobalContants GC_INSTANCE = ConfigFactory.create(GlobalContants.class);
    Duration LONG_TIMEOUT = Duration.ofSeconds(Long.parseLong(GC_INSTANCE.getLongTimeout()));
    Duration SHORT_TIMEOUT = Duration.ofSeconds(Long.parseLong(GC_INSTANCE.getShortTimeout()));
    String UPLOAD_PATH = PROJECT_PATH.concat(FILE_SEPARATOR).concat("UploadFiles").concat(FILE_SEPARATOR);
    String DOWNLOAD_PATH = PROJECT_PATH.concat(FILE_SEPARATOR).concat("DownloadFiles").concat(FILE_SEPARATOR);

    String REPORTNG_SCREENSHOT_PATH = getAbsolutatePathByOS("screenshotReportNG");
    String EXTENT_PATH = getAbsolutatePathByOS("htmlExtent");
    String ALLURE_PATH = getAbsolutatePathByOS("htmlAllure");
    String BROWSER_DRIVER_LOG = getAbsolutatePathByOS("BrowserDriverLogs");
    String MENU_ITEMS_DATA_JSON = TEST_RESOURCES.concat("MenuItem.json");
    String CAR_BRANDS_DATA_JSON = TEST_RESOURCES.concat("CarBrands.json");
    String BRANDS_DATA_JSON = TEST_RESOURCES.concat("GeneralBrands.json");
    String OFFERS_DATA_JSON = TEST_RESOURCES.concat("Offers.json");
    String ARTICLES_DATA_JSON = TEST_RESOURCES.concat("SellYourCarArticles.json");
    String SERVICES_DATA_JSON = TEST_RESOURCES.concat("Services.json");
    String FEATURED_NEW_CAR_OFFERS_DATA_JSON = TEST_RESOURCES.concat("FeaturedNewCarOffers.json");
    String BRAND_MENU_LINKS_HEADER_DATA_JSON = TEST_RESOURCES.concat("BrandMenuLinks.json");

    static String getAbsolutatePathByOS(String folderName) {
        return PROJECT_PATH.concat(FILE_SEPARATOR).concat(folderName).concat(FILE_SEPARATOR);
    }

    /* Map attribute with property file*/
    @Key("DEV_URL")
    String getDevURL();

    @Key("TEST_URL")
    String getTestURL();

    @Key("STAGING_URL")
    String getStagingURL();

    @Key("LIVE_URL")
    String getLiveURL();

    @Key("SHORT_TIMEOUT")
    String getShortTimeout();

    @Key("LONG_TIMEOUT")
    String getLongTimeout();

    @Key("RETRY_NUMBER")
    String getRetryNum();

    /* Browser Stack */
    @Key("BROWSER_USERNAME")
    String getBrowserStackUserName();

    @Key("BROWSER_AUTOMATE_KEY")
    String getBrowserAutomateKey();

    @Key("BROWSER_STACK_URL")
    String getBrowserStackURL();

    /* Sauce Lab */
    @Key("SAUCE_USERNAME")
    String getSauceLabUserName();

    @Key("SAUCE_AUTOMATE_KEY")
    String getSauceLabAutomateKey();

    @Key("SAUCE_LAB_URL")
    String getSauceLabURL();

    /* Lambda Test */
    @Key("LAMBDA_USERNAME")
    String getLambdaUserName();

    @Key("LAMBDA_AUTOMATE_KEY")
    String getLambdaAutomateKey();

    @Key("LAMBDA_LAB_URL")
    String getLambdaURL();

    /* Webpage Slugs*/
    @Key("About_US")
    String getAboutUsSlug();

    @Key("News")
    String getNewsSlug();

    @Key("Customer_Services")
    String getCustomerServicesSlug();

    @Key("Careers")
    String getCareersSlug();

}
