package af.nasri.imdb.network;

import af.nasri.imdb.app.Constants;
import af.nasri.imdb.model.Popular;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(ApiEndpoints.DISCOVER_MOVIE)
    Call<Popular> getPopularMovies(@Query(Constants.SORT_BY) String sortBy);

}
