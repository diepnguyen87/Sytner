package UI.page;

public interface HomePageUI {
    String HEADER_NAVIGATION = "css=nav[aria-label='jump-to navigation']";

    /* Jump point navigation */
    String DYNAMIC_JUMP_POINT = "css=nav a[aria-label*='%s']";

    /* Our brands JP */
    String DYNAMIC_BRAND_IMAGE_BY_NAME = "xpath=//h3[text()='Our brands']/parent::div/following-sibling::div//img[@alt='%s']";

    /* Sell Your Car JP */
    String DYNAMIC_ARTICLE_TITLE = "xpath=//h4[text()='%s']";
    String DYNAMIC_ARTICLE_CONTENT = "xpath=//h4[text()='%s']/following-sibling::p[1]";
    String DYNAMIC_ARTICLE_FIRST_BUTTON = "xpath=//h4[text()='%s']/following-sibling::div/a[text()='%s']";
    String DYNAMIC_ARTICLE_SECOND_BUTTON = "xpath=//h4[text()='%s']/following-sibling::div/a[text()='%s']";

    /* Our Dealerships */
    String DEALERSHIP_TITLE = "xpath=//h2[text()='Find your Nearest Sytner Car Dealership']";
    String DEALERSHIP_POSTCODE_SEARCH_TEXTBOX = "css=input[name='postcodeSearch']";
    String DEALERSHIP_BRAND_TEXTBOX = "id=brand";
    String DEALERSHIP_BRAND_MENU_LISTBOX = "xpath=//ul[@id='brand-menu']/li[text()='%s']";
    String FIND_DEALERSHIP_BUTTON = "id=btnSubmit";

    /* Service, MOT & Repair */
    String DYNAMIC_SERVICE_TITLE = "xpath=//h3[text()='%s']";
    String DYNAMIC_SERVICE_CONTENT = "xpath=//h3[text()='%s']/following-sibling::div[contains(@class, 'body')]";
    String DYNAMIC_SERVICE_BUTTON = "xpath=//h3[text()='%s']/following-sibling::div[contains(@class, 'button')]/a[text()='%s']";

    /* Featured New Car Offers */
    String FEATURED_SECTION = "xpath=//h3[text()='%s']";
    String DYNAMIC_FEATURED_IMAGE = "xpath=//div[@data-testid='subheading' and text()='%s']/ancestor::div[contains(@class, 'card__block')]/preceding-sibling::a";
    //String DYNAMIC_FEATURED_HEADING = "xpath=//article[@data-testid='offer-card']//div[@data-testid='heading' and contains(text(),'%s')]";
    String DYNAMIC_FEATURED_HEADING = "xpath=//article[@data-testid='offer-card']//div[@data-testid='subheading' and contains(text(),'%s')]/preceding-sibling::div";
    String DYNAMIC_FEATURED_SUB_HEADING = "xpath=//article[@data-testid='offer-card']//div[@data-testid='subheading' and contains(text(),'%s')]";
    String DYNAMIC_FEATURED_OFFER = "xpath=//div[@data-testid='subheading' and text()='%s']/parent::header/following-sibling::ul[@data-testid='offer-highlights']//span[text()='%s']";
    String DYNAMIC_LINK_TO_OFFER = "xpath=//div[@data-testid='subheading' and text()='%s']/parent::header/parent::div/following-sibling::a[@data-testid='link-to-offer']";
    String NEXT_SLIDE = "css=button[title='Next slide']";

    String ACCEPT_ALL_COOKIES_BUTTON = "id=onetrust-accept-btn-handler";
    String COOKIE_SDK = "css=div#onetrust-consent-sdk>div.onetrust-pc-dark-filter";
}
