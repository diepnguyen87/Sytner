package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FooterLink {
    private String page;
    private FooterColumn[] columns;

    @Data
    @AllArgsConstructor
    public static class FooterColumn {
        private String name;
        private Link[] links;

        @Data
        @AllArgsConstructor
        public static class Link{
            private String name;
            private String slug;
        }
    }
}
