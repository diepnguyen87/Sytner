package utilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandMenuLinks {
    private String brand;
    private String[] menuLinks;
}
