package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.request.FavoriteRequest;
import peaksoft.response.SimpleResponse;
import peaksoft.service.FavoriteService;

@RestController
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@RequestMapping("/favorite")
public class FavoriteApi {

    private final FavoriteService favoriteService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/save")
    public SimpleResponse save(@RequestBody FavoriteRequest favoriteRequest){
        return favoriteService.saveFavoriteAndDelete(favoriteRequest);
    }


}
