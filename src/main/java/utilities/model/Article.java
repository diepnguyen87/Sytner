package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private String title;
    private String content;
    private String firstBtn;
    private String secondBtn;
}
