package peaksoft.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import peaksoft.entity.Product;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationResponse {

    private List<Product> productProfileResponses;
    private int page;
    private int size;


    public PaginationResponse(List<Product> productProfileResponses, int page, int size) {
        this.productProfileResponses = productProfileResponses;
        this.page = page;
        this.size = size;
    }
}
