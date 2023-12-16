package peaksoft.service;

import peaksoft.entity.Product;
import peaksoft.enums.Category;
import peaksoft.request.ProductRequest;
import peaksoft.request.ProductRequestToProfile;
import peaksoft.request.Request;
import peaksoft.response.*;

import java.util.List;

public interface ProductService {

    ProductSaveResponse saveProduct(ProductRequest productRequest);

    ProductProfileResponse getById(ProductRequestToProfile productRequest);
    Long countFavoritesByProductName(ProductRequestToProfile productRequestToProfile);

    CountProductResponse countProductOfUserByBasket(ProductRequestToProfile productRequestToProfile);
    RequestResponse getAllProductFromUserFavorite(Request request);
    PaginationResponse getAllByFilter(Category category, int minPrice, int maxPrice,int page, int size);

}
