package peaksoft.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BrandResponse {


    @NotNull(message = "must be not null")
    private String brandName;

    public BrandResponse(String brandName) {
        this.brandName = brandName;
    }
}
