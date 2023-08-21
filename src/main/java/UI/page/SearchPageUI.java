package UI.page;

public interface SearchPageUI {

    /* Filters Section */
    String SEARCH_FILTERS_TITLE = "id=search-filters-title";
    String SEARCH_FILTER_SET = "xpath=//span[contains(@id,'search-filters-set') and text()='%s']";
    String ALL_MAKE_OPTION = "css=ul#search-filters-filter-make>li";
    String SEARCH_FILTER_BY_DYNAMIC_CATEGORY_NAME = "xpath=//div[@id='search-filters-categories']//h2/button[contains(text(), '%s')]";
    String DYNAMIC_MAKE_OPTION_BY_NAME = "xpath=//ul[@id='search-filters-filter-make']/li//div[text()='%s']";
    String DYNAMIC_MAKE_NAME = "xpath=//ul[@id='search-filters-filter-make']/li//input[@type='checkbox']/following-sibling::div[text()='%s']";
    String DYNAMIC_MAKE_COUNT = "id=search-filters-filter-make-option-%s-count";
    String MAKE_SHOW_MORE_LESS_BUTTON = "id=search-filters-filter-make-show-more";
    String MAKE_SHOW_MORE_BUTTON = "css=button[id='search-filters-filter-make-show-more'][title='Show more']";
    String MAKE_SHOW_LESS_BUTTON = "css=button[id='search-filters-filter-make-show-more'][title='Show less']";


    /* Category Make */
    String SEARCH_MAKE_TEXTBOX = "id=search-filters-filter-make-searchbox";

    /* Search Results */
    String SEARCH_TEXTBOX = "id=search-searchbox";
    String SEARCH_RESULT_LIST = "css=section#search-results ul[class*='InfiniteHits_row']>li";
    String SEARCH_RESULT_SHOW_MORE = "id=search-results-hits-load-more";

}
