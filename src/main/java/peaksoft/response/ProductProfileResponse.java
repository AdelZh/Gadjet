package peaksoft.response;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.ElementCollection;
import lombok.*;
import peaksoft.entity.Brand;
import peaksoft.entity.Comment;
import peaksoft.enums.Category;

import java.util.List;

@Builder
@Setter
@Getter
public class ProductProfileResponse {

    private String name;
    private int price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;
    private Brand brand;
    private List<Comment> comment;


    public ProductProfileResponse(String name, int price, List<String> images, String characteristic, Boolean isFavorite, String madeIn, Category category, Brand brand, List<Comment> comment) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
        this.comment = comment;
    }
}
