package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FCA {
    private String name;
    private SlugValue[] slugs;

    @Data
    @AllArgsConstructor
    public static class SlugValue{
        private String page;
        private String value;
    }
}
