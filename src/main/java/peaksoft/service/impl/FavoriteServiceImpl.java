package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.entity.Favorite;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.repo.FavoriteRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.request.FavoriteRequest;
import peaksoft.response.SimpleResponse;
import peaksoft.service.FavoriteService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {


    private final FavoriteRepo favoriteRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Override
    public SimpleResponse saveFavoriteAndDelete(FavoriteRequest favoriteRequest) {
        User user = userRepo.getUserByEmail(favoriteRequest.email()).orElseThrow(EntityNotFoundException::new);
        Product product = productRepo.getProductsByName(favoriteRequest.name()).orElseThrow(EntityNotFoundException::new);

        Optional<Favorite> existingFavorite = favoriteRepo.findByUserAndProduct(user, product);

        if (existingFavorite.isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProduct(product);
            favoriteRepo.save(favorite);
            return new SimpleResponse(HttpStatus.OK, "saved successfully");
        } else {
            Favorite favoriteToDelete = existingFavorite.get();
            favoriteRepo.delete(favoriteToDelete);
        }
        return new SimpleResponse(HttpStatus.OK, "deleted");
    }

}

