package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Service {
    private String title;
    private String content;
    private String btn;
    private String slug;
}
