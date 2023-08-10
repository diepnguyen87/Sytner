package components.global;

import commons.BasePage;
import data.DataController;
import utilities.model.FCA;

public class SytnerFooterComp extends BasePage {

    public static String SOCIAL_BAR = "css=div[class*='SocialBar_socialIcons']";
    public static String DYNAMIC_SOCIAL_NETWORK_ICON = "css=a[aria-label='%s']";
    public static String DYNAMIC_HYPERLINK_BY_COLUMN_NAME = "xpath=//h6[text()='%s']/following-sibling::nav//a[contains(text(), '%s')]";
    public static String OUR_LOCATION = "xpath=//footer//h6[text()='Our Location']/following-sibling::div/p";
    public static String DISCLOSURE = "xpath=//footer//h6[text()='Disclosure']/following-sibling::div/p";
    public static String ACCEPTED_PAYMENT_METHOD = "xpath=//footer//h6[text()='Accepted Payment Methods']/following-sibling::a/img";
    public static String DYNAMIC_POLICY_LINKS = "css=footer a[title*='%s']";

    public SytnerFooterComp() {
    }

    public void moveToSocialNetwork() {
        waitForElementVisible(SOCIAL_BAR);
        scrollToElementOnDown(SOCIAL_BAR);
    }

    public String getTargetAttributeSocialNetwork(String socialNetworkName) {
        return getAttributeValue(DYNAMIC_SOCIAL_NETWORK_ICON, "target", socialNetworkName);
    }

    public String getHrefAttributeSocialNetwork(String socialNetworkName) {
        return getAttributeValue(DYNAMIC_SOCIAL_NETWORK_ICON, "href", socialNetworkName);
    }

    public boolean isFooterLinkDisplayed(String columnName, String link) {
        waitForElementVisible(DYNAMIC_HYPERLINK_BY_COLUMN_NAME, columnName, link);
        return isElementDisplayedInDOM(DYNAMIC_HYPERLINK_BY_COLUMN_NAME, columnName, link);
    }

    public String getTargetAttributeFooterLink(String columnName, String link) {
        return getAttributeValue(DYNAMIC_HYPERLINK_BY_COLUMN_NAME, "target", columnName, link);
    }

    public String getHrefAttributeFooterLink(String columnName, String link) {
        return getAttributeValue(DYNAMIC_HYPERLINK_BY_COLUMN_NAME, "href", columnName, link);
    }

    public String getLocation() {
        waitForElementVisible(OUR_LOCATION);
        return getElementText(OUR_LOCATION);
    }

    public String getDisclosure() {
        waitForElementVisible(DISCLOSURE);
        return getElementText(DISCLOSURE);
    }

    public boolean isPaymentMethodDisplayed() {
        waitForElementVisible(ACCEPTED_PAYMENT_METHOD);
        return isElementDisplayedInDOM(ACCEPTED_PAYMENT_METHOD);
    }

    public boolean isPolicyLinkDisplayed(String policyLink) {
        waitForElementVisible(DYNAMIC_POLICY_LINKS, policyLink);
        return isElementDisplayedInDOM(DYNAMIC_POLICY_LINKS, policyLink);
    }

    public String getTargetAttributePolicyLink(String policyLink) {
        waitForElementVisible(DYNAMIC_POLICY_LINKS, policyLink);
        return getAttributeValue(DYNAMIC_POLICY_LINKS, "target", policyLink);
    }

    public String getHrefAttributePolicyLink(String policyLink) {
        waitForElementVisible(DYNAMIC_POLICY_LINKS, policyLink);
        return getAttributeValue(DYNAMIC_POLICY_LINKS, "href", policyLink);
    }

    public String getHrefAttributeFCA() {
        FCA fca = DataController.getFCAAllPage();
        waitForElementVisible(DYNAMIC_POLICY_LINKS, fca.getName());
        return getAttributeValue(DYNAMIC_POLICY_LINKS, "href", fca.getName());
    }

    public String getFCASlugByPageName(String childBrand) {
        FCA.SlugValue[] slugValues = DataController.getFCAAllPage().getSlugs();
        for (FCA.SlugValue slugValue : slugValues) {
            if(slugValue.getPage().equalsIgnoreCase(childBrand)){
                return slugValue.getValue();
            }
        }
        return null;
    }
}
