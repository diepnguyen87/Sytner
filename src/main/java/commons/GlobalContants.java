package commons;

import java.io.File;
import java.time.Duration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"file:src/main/java/commons/GlobalContanst.properties"})
public interface GlobalContants extends Config {

    /* App info */
    GlobalContants GC_INSTANCE = ConfigFactory.create(GlobalContants.class);
    Duration LONG_TIMEOUT = Duration.ofSeconds(Long.parseLong(GC_INSTANCE.getLongTimeout()));
    Duration SHORT_TIMEOUT = Duration.ofSeconds(Long.parseLong(GC_INSTANCE.getShortTimeout()));

    /* System info */
    String PROJECT_PATH = System.getProperty("user.dir");
    String OS_NAME = System.getProperty("os.name");
    String JAVA_VERSION = System.getProperty("java.version");
    String FILE_SEPARATOR = File.separator;

    String UPLOAD_PATH = PROJECT_PATH.concat(FILE_SEPARATOR).concat("UploadFiles").concat(FILE_SEPARATOR);
    String DOWNLOAD_PATH = PROJECT_PATH.concat(FILE_SEPARATOR).concat("DownloadFiles").concat(FILE_SEPARATOR);

    String REPORTNG_SCREENSHOT_PATH = getAbsolutatePathByOS("screenshotReportNG");
    String EXTENT_PATH = getAbsolutatePathByOS("htmlExtent");
    String ALLURE_PATH = getAbsolutatePathByOS("htmlAllure");
    String BROWSER_DRIVER_LOG = getAbsolutatePathByOS("BrowserDriverLogs");
    String ACCOUNT_DATA_JSON = getAbsolutatePathByOS("src").concat(FILE_SEPARATOR)
            .concat("main").concat(FILE_SEPARATOR)
            .concat("java").concat(FILE_SEPARATOR)
            .concat("data").concat(FILE_SEPARATOR)
            .concat("nopcommerce").concat(FILE_SEPARATOR)
            .concat("AccountData.json");

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

}
