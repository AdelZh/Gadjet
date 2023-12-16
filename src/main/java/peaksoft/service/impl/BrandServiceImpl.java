package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Brand;
import peaksoft.repo.BrandRepo;
import peaksoft.request.BrandRequest;
import peaksoft.response.BrandResponse;
import peaksoft.service.BrandService;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    @Override
    public BrandResponse saveBrand(BrandRequest brandRequest) {
        Brand brand=Brand.builder()
                .brandName(brandRequest.brandName())
                .image(brandRequest.image())
                .build();
        brandRepo.save(brand);

        return BrandResponse.builder()
                .brandName(brand.getBrandName())
                .build();
    }
}
