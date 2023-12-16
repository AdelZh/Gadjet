package peaksoft.service.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.entity.Basket;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.BasketRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.request.BasketRequest;
import peaksoft.request.Request2;
import peaksoft.response.SimpleResponse;
import peaksoft.service.BasketService;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final BasketRepo basketRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final JwtService jwtService;


    @Override
    public SimpleResponse saveAndDelete(BasketRequest basketRequest) {
        Product products = productRepo.getProductsByName(basketRequest.name()).orElseThrow(
                () -> {
                    log.error( "product with name: " + basketRequest.name() + "not found");
                    return new NotFoundException(
                            "product with name: " + basketRequest.name() + "not found");
                });

        User user=userRepo.getUserByEmail(basketRequest.email()).orElseThrow(EntityNotFoundException::new);
        Optional<Basket> existingBasket=basketRepo.findByUserAndProduct(user,products);

        if (existingBasket.isEmpty()){
            Basket basket=new Basket();
            basket.setProduct(Collections.singletonList(products));
            basket.setUser(user);

            basketRepo.save(basket);
            return new SimpleResponse(HttpStatus.OK, "saved");
        }else {
            Basket basketToDelete=existingBasket.get();
            basketRepo.delete(basketToDelete);
        }
        return new SimpleResponse(HttpStatus.OK, "deleted");
    }



    @Override
    public SimpleResponse delete(Request2 request) {
        String userName=jwtService.validateToken(request.token());
        User user=userRepo.getUserByEmail(userName).orElseThrow(EntityNotFoundException::new);
        Basket basket=user.getBaskets();
        basketRepo.delete(basket);
        return new SimpleResponse(HttpStatus.OK, "deleted");
    }
}
