package peaksoft.response;


import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import peaksoft.entity.Favorite;
import peaksoft.enums.Category;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductFavoriteResponse {

    @NotNull(message = "must be not null")
    private String name;
    @NotNull(message = "must be not null")
    private int price;
    @ElementCollection
    private List<String> images;
    @NotNull(message = "must be not null")
    private String characteristic;
    @NotNull(message = "must be not null")
    private Boolean isFavorite;
    @NotNull(message = "must be not null")
    private String madeIn;
    @NotNull(message = "must be not null")
    private Category category;
    @NotNull(message = "must be not null")
    private List<Favorite> favorites;

    public ProductFavoriteResponse(String name, int price, List<String> images, String characteristic, Boolean isFavorite, String madeIn, Category category, List<Favorite> favorites) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.favorites = favorites;
    }
}
