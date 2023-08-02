package commons;

import components.global.SytnerFooterComp;
import components.global.SytnerHeaderComp;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class BasePage {

    public WebDriver getDriver(){
        return tdriver.get();
    }
    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        if(tdriver.get() == null){
            tdriver.set(driver);
        }
    }

    /* Cookies */
    public Set<Cookie> getCookies() {
        return getDriver().manage().getCookies();
    }

    public void setCookies(Set<Cookie> cookieSet) {
        for (Cookie cookie : cookieSet) {
            getDriver().manage().addCookie(cookie);
        }
        sleepInSecond(3);
        refreshCurrentPage();
    }

    private Duration shortTimeout = GlobalContants.SHORT_TIMEOUT;
    private Duration longTimeout = GlobalContants.LONG_TIMEOUT;

    /* WEB BROWSER */
    public void openURL(String url) {
        getDriver().get(url);
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String getCurrentURL() {
        return getDriver().getCurrentUrl();
    }

    public String getPageSource() {
        return getDriver().getPageSource();
    }

    public void navigateToURL(String URL){
        getDriver().navigate().to(URL);
    }

    public void backToPage() {
        getDriver().navigate().back();
    }

    public void forwardToPage() {
        getDriver().navigate().forward();
    }

    public void refreshCurrentPage() {
        getDriver().navigate().refresh();
    }

    public Alert waitToAlertPresence() {
        return new WebDriverWait(getDriver(), longTimeout).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptAlert() {
        waitToAlertPresence().accept();
    }

    public void cancelAlert() {
        waitToAlertPresence().dismiss();
    }

    public String getAlertText() {
        return waitToAlertPresence().getText();
    }

    public void sendkeyToAlert(String value) {
        waitToAlertPresence().sendKeys(value);
    }

    public String getWindowHandle() {
        return getDriver().getWindowHandle();
    }
    public Set<String> getWindowHandles() {
        return getDriver().getWindowHandles();
    }

    public void switchToWindowByID(String parentPageID) {
        Set<String> windowList = getWindowHandles();
        for (String window : windowList) {
            if (!window.equals(parentPageID)) {
                getDriver().switchTo().window(window);
            }
        }
    }

    public void switchToWindowByTitle(String expectedPageTitle) {
        Set<String> windowList = getWindowHandles();
        for (String window : windowList) {
            getDriver().switchTo().window(window);
            if (getDriver().getTitle().contains(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(String parentPageID) {
        Set<String> windowList = getWindowHandles();
        for (String window : windowList) {
            getDriver().switchTo().window(window);
            if (!window.equals(parentPageID)) {
                getDriver().close();
            }
        }
        getDriver().switchTo().window(parentPageID);
    }

    /* WEB ELEMENT */

    private String castRestParameter(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    private By getByLocator(String locator, String... dynamicValues) {
        By by = null;
        locator = castRestParameter(locator, dynamicValues);
        int splitSign = locator.indexOf("=");

        String selectorStrategy = locator.toLowerCase().substring(0, splitSign);
        String selectorExpression = locator.substring(splitSign + 1);

        if (selectorStrategy.equals("id")) {
            by = By.id(selectorExpression);
        } else if (selectorStrategy.contains("class")) {
            by = By.className(selectorExpression);
        } else if (selectorStrategy.equals("name")) {
            by = By.name(selectorExpression);
        } else if (selectorStrategy.contains("css")) {
            by = By.cssSelector(selectorExpression);
        } else if (selectorStrategy.equals("xpath")) {
            by = By.xpath(selectorExpression);
        } else {
            throw new RuntimeException("Invalid locator");
        }
        return by;
    }

    public WebElement getElement(String locator, String... dynamicValues) {
        return getDriver().findElement(getByLocator(locator, dynamicValues));
    }

    public List<WebElement> getElements(String locator, String... dynamicValues) {
        return getDriver().findElements(getByLocator(locator, dynamicValues));
    }

    public void clickToElement(String locator, String... dynamicValues) {
        getElement(locator, dynamicValues).click();
    }

    public void sendkeyToElement(String locator, String value, String... dynamicValues) {
        WebElement element = getElement(locator, dynamicValues);
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDefaultDropdown(String locator, String visibleText, String... dynamicValues) {
        new Select(getElement(locator, dynamicValues)).selectByVisibleText(visibleText);
    }

    public List<WebElement> getOptionsInDefaultDropdown(String locator) {
        return new Select(getElement(locator)).getOptions();
    }

    public String getSelectedItemInDefaultDropdown(String locator, String... dynamicValues) {
        return new Select(getElement(locator, dynamicValues)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(String locator) {
        return new Select(getElement(locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedText) {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentXpath))).click();

        List<WebElement> elementList = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement element : elementList) {
            if (element.getText().trim().equalsIgnoreCase(expectedText)) {
                if (element.isDisplayed()) {
                    element.click();
                } else {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
                    element.click();
                }
            }
        }
    }

    public String getAttributeValue(String locator, String attributeName, String... dynamicValues) {
        return getElement(locator, dynamicValues).getAttribute(attributeName);
    }

    public String getElementText(String locator, String... dynamicValues) {
        return getElement(locator, dynamicValues).getText();
    }

    public String getCssValue(String locator, String cssName) {
        return getElement(locator).getCssValue(cssName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(String locator, String... dynamicValues) {
        return getElements(locator, dynamicValues).size();
    }

    public void checkTheCheckBoxOrRadio(String locator, String... dynamicValues) {
        if (!getElement(locator, dynamicValues).isSelected()) {
            clickToElement(locator, dynamicValues);
        }
    }

    public void uncheckTheCheckbox(String locator, String... dynamicValues) {
        if (getElement(locator, dynamicValues).isSelected()) {
            clickToElement(locator, dynamicValues);
        }
    }

    public boolean isElementDisplayedInDOM(String locator, String... dynamicValues) {
        return getElement(locator, dynamicValues).isDisplayed();
    }

    public boolean isElementUnDisplayed(String locator, String... dynamicValues) {
        setImplicitWait(shortTimeout);
        List<WebElement> elementList = getElements(locator, dynamicValues);
        setImplicitWait(longTimeout);

        int size = elementList.size();

        if (size == 0) {
            return true;
        } else if (size > 0 && !elementList.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplicitWait(Duration timeInSecond) {
        getDriver().manage().timeouts().implicitlyWait(timeInSecond);
    }

    public boolean isElementSelected(String locator, String... dynamicValues) {
        return getElement(locator, dynamicValues).isSelected();
    }

    public boolean isElementEnabled(String locator) {
        return getElement(locator).isEnabled();
    }

    public void switchToFrame(String locator) {
        getDriver().switchTo().frame(getElement(locator));
    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public void doubleClickToElement(String locator) {
        new Actions(getDriver()).doubleClick(getElement(locator)).perform();
    }

    public void hoverToElement(String locator, String... dynamicValues) {
        new Actions(getDriver()).moveToElement(getElement(locator, dynamicValues)).perform();
    }

    public void scrollToElement(String locator, String... dynamicValues) {
        new Actions(getDriver()).scrollToElement(getElement(locator, dynamicValues)).perform();
    }

    public void rightClickToElement(String locator) {
        new Actions(getDriver()).contextClick(getElement(locator)).perform();
    }

    public void openPageOnNewTab(String locator, String... dynamicValues){
        new Actions(getDriver()).keyDown(Keys.LEFT_CONTROL).click(getElement(locator, dynamicValues)).keyUp(Keys.LEFT_CONTROL).build().perform();
    }

    public void dragAndDropToElement(String sourceXpath, String targetXpath) {
        new Actions(getDriver()).dragAndDrop(getElement(sourceXpath), getElement(targetXpath)).perform();
    }

    public void sendKeyboardToElement(String locator, Keys key, String... dynamicValues) {
        new Actions(getDriver()).sendKeys(getElement(castRestParameter(locator, dynamicValues)), key).perform();
    }

    public Object executeForBrowser(String javaScript) {
        return ((JavascriptExecutor) getDriver()).executeScript(javaScript);
    }

    public Object executeForBrowser(String javaScript, WebElement element) {
        return ((JavascriptExecutor) getDriver()).executeScript(javaScript, element);
    }

    public String getInnerText() {
        return (String) ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        String textActual = (String) ((JavascriptExecutor) getDriver()).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        ((JavascriptExecutor) getDriver()).executeScript("window.location = '" + url + "'");
        sleepInSecond(5);
    }

    public void hightlightElement(String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        WebElement element = getElement(castRestParameter(locator, dynamicValues));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator, String... dynamicValues) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getElement(locator, dynamicValues));
        sleepInSecond(5);
    }

    public void scrollToElementOnTop(String locator, String... dynamicValues) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", getElement(locator, dynamicValues));
    }

    public void scrollToElementOnDown(String locator, String... dynamicValues) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getElement(locator, dynamicValues));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove, String... dynamicValues) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator, dynamicValues));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) ((JavascriptExecutor) getDriver()).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public boolean waitToJQueryAndJSLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), Duration.ofSeconds(200));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        ExpectedCondition<Boolean> isJQueryLoading = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                System.out.println(LocalTime.now());
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0)");
            }
        };

        ExpectedCondition<Boolean> isJSLoading = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(isJQueryLoading) && explicitWait.until(isJSLoading);
    }

    public void checkTheCheckBoxOrRadioByJS(String locator) {
        if (!getElement(locator).isSelected()) {
            clickToElementByJS(locator);
        }
    }

    public void uncheckTheCheckboxJS(String locator) {
        if (getElement(locator).isSelected()) {
            clickToElementByJS(locator);
        }
    }

    public void waitForElementClickable(String locator, String... dynamicValues) {
        new WebDriverWait(getDriver(), longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementVisible(String locator, String... dynamicValues) {
        new WebDriverWait(getDriver(), longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementInVisibleInDOM(String locator, String... dynamicValues) {
        new WebDriverWait(getDriver(), shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementInVisibleNotInDOM(String locator, String... dynamicValues) {
        setImplicitWait(shortTimeout);
        new WebDriverWait(getDriver(), shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, dynamicValues)));
        setImplicitWait(longTimeout);
    }

    public void waitForAllElementsVisible(String locator, String... dynamicValues) {
        new WebDriverWait(getDriver(), longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator, dynamicValues)));
    }

    public void waitForAllElementsInVisibleInDOM(String locator, String... dynamicValues) {
        new WebDriverWait(getDriver(), shortTimeout).until(ExpectedConditions.invisibilityOfAllElements(getElements(locator, dynamicValues)));
    }

    public void waitForAllElementsInVisibleNotInDOM(String locator, String... dynamicValues) {
        setImplicitWait(shortTimeout);
        new WebDriverWait(getDriver(), shortTimeout).until(ExpectedConditions.invisibilityOfAllElements(getElements(locator, dynamicValues)));
        setImplicitWait(longTimeout);
    }

    public void uploadFiles(String... fileNames) {
        String locator = "css=input[type='file']";
        String fullFilePath = "";
        for (String file : fileNames) {
            fullFilePath += GlobalContants.UPLOAD_PATH.concat(file + "\n");
        }
        fullFilePath = fullFilePath.trim();
        getElement(locator).sendKeys(fullFilePath);
    }

    public void sleepInSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(getDriver(), GlobalContants.LONG_TIMEOUT);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        ExpectedCondition<Boolean> isDocumentLoading = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(isDocumentLoading);
    }

    public String getnCloseCurrentURL(String parentWindow) {
        String currentURL = getDriver().getCurrentUrl();
        getDriver().close();
        getDriver().switchTo().window(parentWindow);
        return currentURL;
    }

    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();




    /* Sytner Header and Footer */
    private static ThreadLocal<SytnerHeaderComp> tSytnerHeader = new ThreadLocal<>();
    private SytnerFooterComp sytnerFooter;

    public SytnerHeaderComp getSytnerHeaderComp(){
        if(tSytnerHeader.get() == null){
            tSytnerHeader.set(new SytnerHeaderComp());
        }
        return tSytnerHeader.get();
    }
}
