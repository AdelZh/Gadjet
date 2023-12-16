package peaksoft.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Product;
import java.util.List;

@Getter
@Setter
@Builder
public class RequestResponse {


    private List<Product> products;


    public RequestResponse(List<Product> products) {
        this.products = products;
    }
}
