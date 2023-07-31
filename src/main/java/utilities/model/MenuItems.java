package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItems {

    private String sectionName;
    private Item[] items;

    @Data
    @AllArgsConstructor
    public static class Item {
        private String name;
        private String slug;
    }
}
