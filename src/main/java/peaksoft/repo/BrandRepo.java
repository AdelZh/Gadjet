package peaksoft.repo;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Brand;

import java.util.Optional;


@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {


    @Query("select b from Brand b where b.brandName = :brandName")
    Optional<Brand> getBrandByBrandName(@Param("brandName") String brandName);
}
