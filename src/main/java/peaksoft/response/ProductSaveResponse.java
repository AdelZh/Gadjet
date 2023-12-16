package peaksoft.response;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductSaveResponse {

    private String name;
    public ProductSaveResponse(String name) {
        this.name = name;
    }

}
