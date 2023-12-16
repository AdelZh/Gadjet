package peaksoft.service;

import peaksoft.request.BasketRequest;
import peaksoft.request.Request2;
import peaksoft.response.BasketResponse;
import peaksoft.response.SimpleResponse;

public interface BasketService {

    SimpleResponse saveAndDelete(BasketRequest basketRequest);

    public SimpleResponse delete(Request2 request);


}
