package data;

import commons.GlobalContants;
import org.testng.annotations.DataProvider;
import utilities.DataObjectBuilder;
import utilities.model.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DataController {

    private static MenuItems[] menuItemData;
    private static Brand[] brandData;
    private static Offer[] offerData;
    private static Article[] articleData;
    private static Service[] serviceData;
    private static FeaturedNewCarOffers[] featuredNewCarOfferData;

    @DataProvider(name = "menuItems")
    public static MenuItems[] menuItemDataSet() {
        if(menuItemData == null){
            menuItemData = DataObjectBuilder.buildDataObject(GlobalContants.MENU_ITEMS_DATA_JSON, MenuItems[].class);
        }
       return menuItemData;
    }

    @DataProvider(name = "carBrands")
    public static Brand[] carBrandDataSet() {
        if (brandData == null) {
            return DataObjectBuilder.buildDataObject(GlobalContants.CAR_BRANDS_DATA_JSON, Brand[].class);
        }
        return brandData;
    }

    public static String getSlugByBrandName(String brandName) {

        if (brandData == null) {
            brandData = DataObjectBuilder.buildDataObject(GlobalContants.CAR_BRANDS_DATA_JSON, Brand[].class);
        }

        return Arrays.stream(brandData).filter(brand -> brand.getName().equalsIgnoreCase(brandName)).limit(1).collect(Collectors.toList()).get(0).getSlug();
    }

    @DataProvider(name = "brands")
    public static String[] brandDataSet() {
        return DataObjectBuilder.buildDataObject(GlobalContants.BRANDS_DATA_JSON, Brands.class).getBrands();
    }

    @DataProvider(name = "offers")
    public static Offer[] offersDataSet() {
        if(offerData == null){
            offerData = DataObjectBuilder.buildDataObject(GlobalContants.OFFERS_DATA_JSON, Offer[].class);
        }
        return offerData;
    }

    @DataProvider(name = "articles")
    public static Article[] articleDataSet() {
        if(articleData == null){
            articleData = DataObjectBuilder.buildDataObject(GlobalContants.ARTICLES_DATA_JSON, Article[].class);
        }
        return articleData;
    }

    @DataProvider(name = "services")
    public static Service[] serviceDataSet() {
        if(serviceData == null){
            serviceData = DataObjectBuilder.buildDataObject(GlobalContants.SERVICES_DATA_JSON, Service[].class);
        }
        return serviceData;
    }

    @DataProvider(name = "featuredNewCarOffers")
    public static FeaturedNewCarOffers[] featuredNewCarOfferDataSet() {
        if(featuredNewCarOfferData == null){
            featuredNewCarOfferData = DataObjectBuilder.buildDataObject(GlobalContants.FEATURED_NEW_CAR_OFFERS_DATA_JSON, FeaturedNewCarOffers[].class);
        }
        return featuredNewCarOfferData;
    }

    @DataProvider(name = "brandMenuLinks")
    public static String[] brandMenuLinkDataSet(String brandName) {
        BrandMenuLinks[] brandMenuLinks = DataObjectBuilder.buildDataObject(GlobalContants.BRAND_MENU_LINKS_HEADER_DATA_JSON, BrandMenuLinks[].class);
        return Arrays.stream(brandMenuLinks).filter(row -> row.getBrand().equalsIgnoreCase(brandName)).limit(1).collect(Collectors.toList()).get(0).getMenuLinks();
    }
    @DataProvider(name = "footerLinksByPage")
    public static FooterLink.FooterColumn[] footerLinkDataSetByPage(String pageName){
        FooterLink[] footerLinks = DataObjectBuilder.buildDataObject(GlobalContants.FOOTER_LINKS, FooterLink[].class);
        return Arrays.stream(footerLinks).filter(page -> page.getPage().equalsIgnoreCase(pageName)).limit(1).collect(Collectors.toList()).get(0).getColumns();
    }

    @DataProvider(name = "policyLinks")
    public static PolicyLink[] policyLinkDataSet(){
        return DataObjectBuilder.buildDataObject(GlobalContants.POLICY_LINKS, PolicyLink[].class);
    }

    @DataProvider(name = "fcaAllPage")
    public static FCA getFCAAllPage(){
        return DataObjectBuilder.buildDataObject(GlobalContants.FCA_LINKS, FCA.class);
    }

    @DataProvider(name = "socialNetworks")
    public static SocialLink.SocialNetwork[] socialNetworkDataSetByPage(String pageName){
        SocialLink[] socialLinks = DataObjectBuilder.buildDataObject(GlobalContants.SOCIAL_NETWORKS, SocialLink[].class);
        return Arrays.stream(socialLinks).filter(page -> page.getPage().equalsIgnoreCase(pageName)).limit(1).collect(Collectors.toList()).get(0).getSocialNetworks();
    }

}
