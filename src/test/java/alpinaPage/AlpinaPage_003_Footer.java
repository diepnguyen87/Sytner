package alpinaPage;

import commons.BaseTest;
import components.global.SytnerFooterComp;
import components.global.SytnerHeaderComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PageGeneratorManager;
import pages.SearchPage;
import pages.bmw.AlpinaPage;
import utilities.model.*;

import java.util.Arrays;

public class AlpinaPage_003_Footer extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        homePage.getSytnerHeaderComp().openBrandPageByName(childBrand);
        alpinaPage = PageGeneratorManager.getAlpinaPage(driver);
        sytnerFooterComp = alpinaPage.getSytnerFooterComp();
        alpinaURL = appURL.concat(DataController.getSlugByBrandName(childBrand));
    }

    @Test
    public void Footer_001_SocialNetworks() {
        sytnerFooterComp.moveToSocialNetwork();

        SocialLink.SocialNetwork[] socialNetworks = DataController.socialNetworkDataSetByPage("Alpina");
        for (SocialLink.SocialNetwork network : socialNetworks) {
            String name = network.getName();
            Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(name), "_blank");
            Assert.assertTrue(sytnerFooterComp.getHrefAttributeSocialNetwork(name).contains(network.getUrl()));
        }
    }

    @Test
    public void Footer_002_FooterLinks() {
        FooterLink.FooterColumn[] footerColumns = DataController.footerLinkDataSetByPage(parentBrand);

        Arrays.stream(footerColumns).forEach(column -> {
            String columnName = column.getName();
            FooterLink.FooterColumn.Link[] links = column.getLinks();

            for (FooterLink.FooterColumn.Link link : links) {
                String linkName = link.getName();
                Assert.assertTrue(sytnerFooterComp.isFooterLinkDisplayed(columnName, linkName));
                Assert.assertEquals(sytnerFooterComp.getTargetAttributeFooterLink(columnName, linkName), "");
                String a = sytnerFooterComp.getHrefAttributeFooterLink(columnName, linkName);
                String b = appURL.concat(link.getSlug());
                boolean c = a.contains(b);
                if(c == false){
                    System.out.println("stop");
                }
                Assert.assertTrue(sytnerFooterComp.getHrefAttributeFooterLink(columnName, linkName).contains(appURL.concat(link.getSlug())));

            }
        });
    }

    @Test
    public void Footer_003_OurLocation() {
        String expectedLocation = "Reg. Office: 2 Penman Way, Grove Park, Leicester LE19 1ST\n" +
                "Registered in England. Company Reg. No. 2883766\n" +
                "FCA Reg. No 310540 | VAT Reg. No GB 610 6250 86";
        Assert.assertEquals(sytnerFooterComp.getLocation(), expectedLocation);
    }

    @Test
    public void Footer_004_Disclosure() {
        String expectedDisclosure = "Sytner Group Limited is authorised and regulated by the FCA for Insurance distribution activities, under FRN 310540.";
        Assert.assertEquals(sytnerFooterComp.getDisclosure(), expectedDisclosure);
    }

    @Test
    public void Footer_005_AcceptedPaymentMethods() {
        Assert.assertTrue(sytnerFooterComp.isPaymentMethodDisplayed());
    }

    @Test(dataProvider = "policyLinks", dataProviderClass = DataController.class)
    public void Footer_006_PolicyLinks(PolicyLink policy) {
        Assert.assertTrue(sytnerFooterComp.isPolicyLinkDisplayed(policy.getName()));
        Assert.assertEquals(sytnerFooterComp.getTargetAttributePolicyLink(policy.getName()), "");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributePolicyLink(policy.getName()), appURL.concat(policy.getSlug()));
    }


    private SytnerFooterComp sytnerFooterComp;
    private AlpinaPage alpinaPage;
    private String alpinaURL;
    private String parentBrand = "BMW";
    private String childBrand = "Alpina";
}
