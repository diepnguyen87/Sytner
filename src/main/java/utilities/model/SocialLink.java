package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialLink {

    private String page;
    private SocialNetwork[] socialNetworks;

    @Data
    @AllArgsConstructor
    public class SocialNetwork{
        private String name;
        private String url;
    }
}
