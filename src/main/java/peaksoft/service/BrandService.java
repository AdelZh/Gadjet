package peaksoft.service;

import peaksoft.request.BrandRequest;
import peaksoft.response.BrandResponse;

public interface BrandService {


    BrandResponse saveBrand(BrandRequest brandRequest);
}
