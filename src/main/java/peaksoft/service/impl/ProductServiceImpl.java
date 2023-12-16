package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.entity.Basket;
import peaksoft.entity.Brand;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.enums.Category;
import peaksoft.repo.BrandRepo;
import peaksoft.repo.FavoriteRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.request.ProductRequest;
import peaksoft.request.ProductRequestToProfile;
import peaksoft.request.Request;
import peaksoft.response.*;
import peaksoft.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final FavoriteRepo favoriteRepo;
    private final UserRepo userRepo;


    @Override
    public ProductSaveResponse saveProduct(ProductRequest productRequest) {
        Brand brand = brandRepo.getBrandByBrandName(
                productRequest.brandName()).orElseThrow(EntityNotFoundException::new);

        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .images(productRequest.images())
                .characteristic(productRequest.characteristic())
                .isFavorite(productRequest.isFavorite())
                .madeIn(productRequest.madeIn())
                .category(productRequest.category())
                .build();

        product.setBrand(brand);
        productRepo.save(product);
        return ProductSaveResponse.builder()
                .name(product.getName())
                .build();
    }


    @Override
    public ProductProfileResponse getById(ProductRequestToProfile productRequest) {
        Product product = productRepo.getProductsByName(productRequest.name()).orElseThrow(EntityNotFoundException::new);

        return ProductProfileResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .images(product.getImages())
                .characteristic(product.getCharacteristic())
                .isFavorite(product.getIsFavorite())
                .madeIn(product.getMadeIn())
                .category(product.getCategory())
                .brand(product.getBrand())
                .comment(product.getComment())
                .build();
    }


    @Override
    public Long countFavoritesByProductName(ProductRequestToProfile productRequestToProfile) {
        return favoriteRepo.countByProductName(productRequestToProfile.name());
    }

    @Override
    public CountProductResponse countProductOfUserByBasket(ProductRequestToProfile productRequestToProfile) {
        User user = userRepo.getUserByEmail(productRequestToProfile.name()).orElseThrow(EntityNotFoundException::new);
        Basket basket = user.getBaskets();

        long count = 0L;
        long totalPrice = 0L;

        for (Product product : basket.getProduct()) {
            count++;
            totalPrice += product.getPrice();
        }
        return new CountProductResponse(count, totalPrice);

    }

    @Override
    public PaginationResponse getAllByFilter(Category category, int minPrice, int maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Product> product=
                productRepo.findByCategoryAndPriceBetweenOrderByPrice(category, minPrice, maxPrice, pageable);
        return PaginationResponse.builder()
                .productProfileResponses(product.getContent())
                .page(product.getNumber() + 1)
                .size(product.getTotalPages())
                .build();
    }


    @Override
    public RequestResponse getAllProductFromUserFavorite(Request request) {
        List<Product> products=productRepo.findByFavorite(request.email());

        return RequestResponse.builder()
                .products(products)
                .build();
    }
}





















