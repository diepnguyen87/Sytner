package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class BasePage {

    /* Cookies */
    public Set<Cookie> getCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookieSet) {
        for (Cookie cookie : cookieSet) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
        refreshCurrentPage(driver);
    }

    private Duration shortTimeout = GlobalContants.SHORT_TIMEOUT;
    private Duration longTimeout = GlobalContants.LONG_TIMEOUT;

    /* WEB BROWSER */
    public void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitToAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void acceptAlert(WebDriver driver) {
        waitToAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitToAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitToAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        waitToAlertPresence(driver).sendKeys(value);
    }

    public Set<String> getWindowHandles(WebDriver driver) {
        return driver.getWindowHandles();
    }

    public void switchToWindowByID(WebDriver driver, String parentPageID) {
        Set<String> windowList = getWindowHandles(driver);
        for (String window : windowList) {
            if (!window.equals(parentPageID)) {
                driver.switchTo().window(window);
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
        Set<String> windowList = getWindowHandles(driver);
        for (String window : windowList) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String parentPageID) {
        Set<String> windowList = getWindowHandles(driver);
        for (String window : windowList) {
            driver.switchTo().window(window);
            if (!window.equals(parentPageID)) {
                driver.close();
            }
        }
        driver.switchTo().window(parentPageID);
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

    public WebElement getElement(WebDriver driver, String locator, String... dynamicValues) {
        return driver.findElement(getByLocator(locator, dynamicValues));
    }

    public List<WebElement> getElements(WebDriver driver, String locator, String... dynamicValues) {
        return driver.findElements(getByLocator(locator, dynamicValues));
    }

    public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
        getElement(driver, locator, dynamicValues).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value, String... dynamicValues) {
        WebElement element = getElement(driver, locator, dynamicValues);
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String visibleText, String... dynamicValues) {
        new Select(getElement(driver, locator, dynamicValues)).selectByVisibleText(visibleText);
    }

    public List<WebElement> getOptionsInDefaultDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getOptions();
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... dynamicValues) {
        return new Select(getElement(driver, locator, dynamicValues)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedText) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentXpath))).click();

        List<WebElement> elementList = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement element : elementList) {
            if (element.getText().trim().equalsIgnoreCase(expectedText)) {
                if (element.isDisplayed()) {
                    element.click();
                } else {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                    element.click();
                }
            }
        }
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... dynamicValues) {
        return getElement(driver, locator, dynamicValues).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, locator, dynamicValues).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String cssName) {
        return getElement(driver, locator).getCssValue(cssName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locator, String... dynamicValues) {
        return getElements(driver, locator, dynamicValues).size();
    }

    public void checkTheCheckBoxOrRadio(WebDriver driver, String locator, String... dynamicValues) {
        if (!getElement(driver, locator, dynamicValues).isSelected()) {
            clickToElement(driver, locator, dynamicValues);
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator, String... dynamicValues) {
        if (getElement(driver, locator, dynamicValues).isSelected()) {
            clickToElement(driver, locator, dynamicValues);
        }
    }

    public boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, locator, dynamicValues).isDisplayed();
    }

    public boolean isElementUnDisplayed(WebDriver driver, String locator, String... dynamicValues) {
        setImplicitWait(driver, shortTimeout);
        List<WebElement> elementList = getElements(driver, locator, dynamicValues);
        setImplicitWait(driver, longTimeout);

        int size = elementList.size();

        if (size == 0) {
            return true;
        } else if (size > 0 && !elementList.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplicitWait(WebDriver driver, Duration timeInSecond) {
        driver.manage().timeouts().implicitlyWait(timeInSecond);
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, locator, dynamicValues).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator, String... dynamicValues) {
        new Actions(driver).moveToElement(getElement(driver, locator, dynamicValues)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    public void dragAndDropToElement(WebDriver driver, String sourceXpath, String targetXpath) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... dynamicValues) {
        new Actions(driver).sendKeys(getElement(driver, castRestParameter(locator, dynamicValues)), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public Object executeForBrowser(WebDriver driver, String javaScript, WebElement element) {
        return ((JavascriptExecutor) driver).executeScript(javaScript, element);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(5);
    }

    public void hightlightElement(WebDriver driver, String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, castRestParameter(locator, dynamicValues));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator, dynamicValues));
        sleepInSecond(5);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator, dynamicValues));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        WebElement element = getElement(driver, locator);
        element.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(driver, locator));
        return status;
    }

    public boolean waitToJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(200));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

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

    public void checkTheCheckBoxOrRadioByJS(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            clickToElementByJS(driver, locator);
        }
    }

    public void uncheckTheCheckboxJS(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            clickToElementByJS(driver, locator);
        }
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementInVisibleInDOM(WebDriver driver, String locator, String... dynamicValues) {
        new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, dynamicValues)));
    }

    public void waitForElementInVisibleNotInDOM(WebDriver driver, String locator, String... dynamicValues) {
        setImplicitWait(driver, shortTimeout);
        new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator, dynamicValues)));
        setImplicitWait(driver, longTimeout);
    }

    public void waitForAllElementsVisible(WebDriver driver, String locator, String... dynamicValues) {
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator, dynamicValues)));
    }

    public void waitForAllElementsInVisibleInDOM(WebDriver driver, String locator, String... dynamicValues) {
        new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locator, dynamicValues)));
    }

    public void waitForAllElementsInVisibleNotInDOM(WebDriver driver, String locator, String... dynamicValues) {
        setImplicitWait(driver, shortTimeout);
        new WebDriverWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locator, dynamicValues)));
        setImplicitWait(driver, longTimeout);
    }

    public void uploadFiles(WebDriver driver, String... fileNames) {
        String locator = "css=input[type='file']";
        String fullFilePath = "";
        for (String file : fileNames) {
            fullFilePath += GlobalContants.UPLOAD_PATH.concat(file + "\n");
        }
        fullFilePath = fullFilePath.trim();
        getElement(driver, locator).sendKeys(fullFilePath);
    }

    public void sleepInSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalContants.LONG_TIMEOUT);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> isDocumentLoading = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(isDocumentLoading);
    }
}
