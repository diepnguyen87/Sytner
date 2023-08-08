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

import java.util.Arrays;

public class HomePage_004_Footer extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        sytnerFooterComp = homePage.getSytnerFooterComp();
    }

    @Test
    public void FooterHeader_001_SocialNetworks() {
        String socialNetworkName = "Instagram";
        String expectedSocialURL = "https://www.instagram.com/sytnergroup";

        sytnerFooterComp.moveToSocialNetwork(socialNetworkName);

        Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(socialNetworkName), "_blank");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeSocialNetwork(socialNetworkName), expectedSocialURL);


        socialNetworkName = "Facebook";
        expectedSocialURL = "https://www.facebook.com/SytnerGroup";

        Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(socialNetworkName), "_blank");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeSocialNetwork(socialNetworkName), expectedSocialURL);

        socialNetworkName = "LinkedIn";
        expectedSocialURL = "https://www.linkedin.com/company/sytner-group";

        Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(socialNetworkName), "_blank");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeSocialNetwork(socialNetworkName), expectedSocialURL);

        socialNetworkName = "Youtube";
        expectedSocialURL = "https://www.youtube.com/SytnerGroup";

        Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(socialNetworkName), "_blank");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeSocialNetwork(socialNetworkName), expectedSocialURL);

        socialNetworkName = "Twitter";
        expectedSocialURL = "https://www.twitter.com/Sytner/?lang=en/?lang=en";

        Assert.assertEquals(sytnerFooterComp.getTargetAttributeSocialNetwork(socialNetworkName), "_blank");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributeSocialNetwork(socialNetworkName), expectedSocialURL);
    }

    @Test
    public void FooterHeader_002_FooterLinks() {
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
    public void FooterHeader_003_OurLocation() {
        String expectedLocation = "Reg. Office: 2 Penman Way, Grove Park, Leicester LE19 1ST\n" +
                "Registered in England. Company Reg. No. 2883766\n" +
                "FCA Reg. No 310540 | VAT Reg. No GB 610 6250 86";
        Assert.assertEquals(sytnerFooterComp.getLocation(), expectedLocation);
    }

    @Test
    public void FooterHeader_004_Disclosure() {
        String expectedDisclosure = "Sytner Group Limited is authorised and regulated by the FCA for Insurance distribution activities, under FRN 310540.";
        Assert.assertEquals(sytnerFooterComp.getDisclosure(), expectedDisclosure);
    }

    @Test
    public void FooterHeader_005_AcceptedPaymentMethods() {
        Assert.assertTrue(sytnerFooterComp.isPaymentMethodDisplayed());
    }

    @Test(dataProvider = "policyLinks", dataProviderClass = DataController.class)
    public void FooterHeader_006_PolicyLinks(PolicyLink policy) {
        Assert.assertTrue(sytnerFooterComp.isPolicyLinkDisplayed(policy.getName()));
        Assert.assertEquals(sytnerFooterComp.getTargetAttributePolicyLink(policy.getName()), "");
        Assert.assertEquals(sytnerFooterComp.getHrefAttributePolicyLink(policy.getName()), appURL.concat(policy.getSlug()));
    }

    private SytnerFooterComp sytnerFooterComp;
}
