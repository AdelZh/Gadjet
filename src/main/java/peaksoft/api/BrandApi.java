package peaksoft.api;


import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.request.BrandRequest;
import peaksoft.response.BrandResponse;
import peaksoft.service.BrandService;

@RestController
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandApi {


    private final BrandService brandService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/save")
    public BrandResponse save(@RequestBody BrandRequest brandRequest){
        return brandService.saveBrand(brandRequest);
    }

}
