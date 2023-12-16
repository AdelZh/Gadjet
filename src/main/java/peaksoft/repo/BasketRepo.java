package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Basket;
import peaksoft.entity.Product;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

public interface BasketRepo extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUserAndProduct(User user, Product product);




}



