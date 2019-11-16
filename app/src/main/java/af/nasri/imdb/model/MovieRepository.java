package af.nasri.imdb.model;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import af.nasri.imdb.network.ApiClient;
import af.nasri.imdb.network.ApiServices;

public class MovieRepository {

    private final String TAG = "MovieRepository";
    private LiveData<PagedList<Movie>> popularMovies;
    private MovieDataSourceFactory dataSourceFactory;
    private ApiServices apiServices;

    public MovieRepository() {
        apiServices = ApiClient.getApiService();
        dataSourceFactory = new MovieDataSourceFactory(apiServices);
    }

    LiveData<PagedList<Movie>> getPopularMovies() {
        PagedList.Config pageListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .setInitialLoadSizeHint(10)
                .build();
        popularMovies = new LivePagedListBuilder<>(dataSourceFactory, pageListConfig).build();
        return popularMovies;
    }

}
