package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeaturedNewCarOffers {
    private String slug;
    private String heading;
    private String subHeading;
    private String[] offers;
}
