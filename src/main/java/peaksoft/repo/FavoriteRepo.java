package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.entity.User;

import java.util.Optional;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {

    @Query("select count(f) from Favorite f where f.product.name = :name")
    Long countByProductName(@Param("name") String name);
    Optional<Favorite> findByUserAndProduct(User user, Product product);

}


