package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.request.BasketRequest;
import peaksoft.request.Request2;
import peaksoft.response.SimpleResponse;
import peaksoft.service.BasketService;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
@RequestMapping("/basket")
public class BasketApi {

    private final BasketService basketService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping
    public SimpleResponse saveAndDelete(@RequestBody BasketRequest basketRequest) {
        return basketService.saveAndDelete(basketRequest);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping
    public SimpleResponse delete(@RequestBody Request2 request2){
        return basketService.delete(request2);
    }

}
