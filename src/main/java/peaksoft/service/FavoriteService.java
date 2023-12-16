package peaksoft.service;

import peaksoft.request.FavoriteRequest;
import peaksoft.response.SimpleResponse;

public interface FavoriteService {


    SimpleResponse saveFavoriteAndDelete(FavoriteRequest favoriteRequest);



}
