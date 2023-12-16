package peaksoft.request;

import java.util.List;

public record BasketRequest(

        //List<String> name,
        String token,
        String name,
        String email
) {

}
