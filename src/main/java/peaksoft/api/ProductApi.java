package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.enums.Category;
import peaksoft.request.ProductRequest;
import peaksoft.request.ProductRequestToProfile;
import peaksoft.request.Request;
import peaksoft.response.*;
import peaksoft.service.ProductService;


@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
@RequestMapping("/products")
public class ProductApi {

    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public ProductSaveResponse save(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/get")
    public ProductProfileResponse getByUsername(@RequestBody ProductRequestToProfile productRequest) {
        return productService.getById(productRequest);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/count")
    public ResponseEntity<Long> countFavorites(@RequestBody ProductRequestToProfile productRequest) {
        Long count = productService.countFavoritesByProductName(productRequest);
        return ResponseEntity.ok(count);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/count2")
    public CountProductResponse count(@RequestBody ProductRequestToProfile productRequestToProfile){
       return productService.countProductOfUserByBasket(productRequestToProfile);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/filter")
    public PaginationResponse getFilteredAndSortedProducts(
            @RequestParam(name = "category") Category category,
            @RequestParam(name = "minPrice") int minPrice,
            @RequestParam(name = "maxPrice") int maxPrice,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {

        return productService.getAllByFilter(category, minPrice, maxPrice, page, size);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/getAll")
    public RequestResponse getAll(@RequestBody Request request){
        return productService.getAllProductFromUserFavorite(request);
    }
}













































































   /* @GetMapping("/filter")
    public ResponseEntity<List<Product>> getFilteredAndSortedProducts(
            @RequestParam(name = "category") Category category,
            @RequestParam(name = "minPrice") int minPrice,
            @RequestParam(name = "maxPrice") int maxPrice
           ) {

        List<Product> filteredProducts = productService.getAll(category, minPrice, maxPrice);
        return ResponseEntity.ok(filteredProducts);
    }


   // List<Product> findByCategoryAndPriceBetweenOrderByPrice(Category, int minPrice, int maxPrice);

   // @Query("select p from Product p where p.name in :name")
      List<Product> getProductsByNameIn(@Param("name") List<String> name);

    */
