package peaksoft.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Product;
import peaksoft.enums.Category;
import peaksoft.response.ProductProfileResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


    @Query("select p from Product p where p.name = :name")
    Optional<Product> getProductsByName(@Param("name") String name);

    Page<Product> findByCategoryAndPriceBetweenOrderByPrice(Category category, int minPrice, int maxPrice, Pageable pageable);

    @Query("select p from Product p join p.favorite f where f.user.email = :name")
    List<Product> findByFavorite(@Param("name") String name);

}
