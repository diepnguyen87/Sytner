package homePage;

import commons.BaseTest;
import commons.GlobalContants;
import components.global.SytnerFooterComp;
import data.DataController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.model.FooterLink;
import utilities.model.PolicyLink;
import utilities.model.SocialLink;

import java.util.Arrays;

public class HomePage_004_Footer extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        sytnerFooterComp = homePage.getSytnerFooterComp();
    }

    public void Footer_001_SocialNetworks(SocialLink.SocialNetwork socialNetwork) {
        sytnerFooterComp.moveToSocialNetwork();

        SocialLink.SocialNetwork[] socialNetworks = DataController.socialNetworkDataSetByPage("Home");
        for (SocialLink.SocialNetwork network : socialNetworks) {
            String name = network.getName();
            Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(name), "_blank");
            Assert.assertTrue(sytnerFooterComp.getHrefAttributeSocialNetwork(name).contains(network.getUrl()));
        }
    }

    @Test
    public void Footer_002_FooterLinks() {
        FooterLink.FooterColumn[] footerColumns = DataController.footerLinkDataSetByPage("general");

        Arrays.stream(footerColumns).forEach(column -> {
            String columnName = column.getName();
            FooterLink.FooterColumn.Link[] links = column.getLinks();

            for (FooterLink.FooterColumn.Link link : links) {
                String linkName = link.getName();
                Assert.assertTrue(sytnerFooterComp.isFooterLinkDisplayed(columnName, linkName));
                Assert.assertEquals(sytnerFooterComp.getTargetAttributeFooterLink(columnName, linkName), "");
                Assert.assertTrue(sytnerFooterComp.getHrefAttributeFooterLink(columnName, linkName).contains(appURL.concat(link.getSlug())));
            }
        });
    }

    @Test
    public void Footer_003_OurLocation() {
        Assert.assertEquals(sytnerFooterComp.getLocation(), GlobalContants.SYTNER_LOCATION);
    }

    @Test
    public void Footer_004_Disclosure() {
        Assert.assertEquals(sytnerFooterComp.getDisclosure(), GlobalContants.SYTNER_DISCLOSURE);
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
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeFCA(), appURL.concat(sytnerFooterComp.getFCASlugByPageName("General")));
    }

    private SytnerFooterComp sytnerFooterComp;
    private String parentBrand = "Home";
    private String childBrand = "Home";
}
