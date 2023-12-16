package peaksoft.request;

import peaksoft.entity.Comment;
import peaksoft.enums.Category;

import java.util.List;


public record ProductRequest(
        String name,
        int price,
        List<String> images,
        String characteristic,
        Boolean isFavorite,
        String madeIn,
        Category category,
        String brandName,
        Comment comment
) {
}

