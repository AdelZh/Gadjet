package peaksoft.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Product;

import java.util.List;

@Getter
@Setter
@Builder
public class BasketResponse {

    @NotNull(message = "must be not null")
    private List<String> name;
    @NotNull(message = "must be not null")
    private String email;

    public BasketResponse(List<String> name, String email) {
        this.name=name;
        this.email = email;
    }
}
