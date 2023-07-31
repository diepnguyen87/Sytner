package data;

import commons.GlobalContants;
import utilities.DataObjectBuilder;
import utilities.model.*;
import org.testng.annotations.DataProvider;

public class DataController {

    @DataProvider(name = "menuItems")
    public static MenuItems[] menuItemDataSet() {
        MenuItems[] menuItemData = DataObjectBuilder.buildDataObject(GlobalContants.MENU_ITEMS_DATA_JSON, MenuItems[].class);
        return menuItemData;
    }

    @DataProvider(name = "carBrands")
    public static Brand[] carBrandDataSet() {
        return DataObjectBuilder.buildDataObject(GlobalContants.CAR_BRANDS_DATA_JSON, Brand[].class);
    }

    @DataProvider(name = "brands")
    public static String[] brandDataSet() {
        return DataObjectBuilder.buildDataObject(GlobalContants.BRANDS_DATA_JSON, Brands.class).getBrands();
    }

    @DataProvider(name = "offers")
    public static Offer[] offersDataSet() {
        return DataObjectBuilder.buildDataObject(GlobalContants.OFFERS_DATA_JSON, Offer[].class);
    }

    @DataProvider(name = "articles")
    public static Article[] articleDataSet(){
        return DataObjectBuilder.buildDataObject(GlobalContants.ARTICLES_DATA_JSON, Article[].class);
    }

    @DataProvider(name = "services")
    public static Service[] serviceDataSet(){
        return DataObjectBuilder.buildDataObject(GlobalContants.SERVICES_DATA_JSON, Service[].class);
    }

    @DataProvider(name = "featuredNewCarOffers")
    public static FeaturedNewCarOffers[] featuredNewCarOfferDataSet(){
        return DataObjectBuilder.buildDataObject(GlobalContants.FEATURED_NEW_CAR_OFFERS_DATA_JSON, FeaturedNewCarOffers[].class);
    }
}