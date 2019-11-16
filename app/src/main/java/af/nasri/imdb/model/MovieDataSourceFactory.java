package af.nasri.imdb.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import af.nasri.imdb.network.ApiServices;

public class MovieDataSourceFactory extends DataSource.Factory<Long, Movie> {

    private ApiServices apiServices;
    private MutableLiveData<MovieDataSource> movieDataSourceLiveData;
    private MovieDataSource movieDataSource;

    public MovieDataSourceFactory(ApiServices apiServices) {
        this.apiServices = apiServices;
        movieDataSourceLiveData = new MutableLiveData<>();
    }


    @NonNull
    @Override
    public DataSource<Long, Movie> create() {
        movieDataSource = new MovieDataSource(apiServices);
        movieDataSourceLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceLiveData() {
        return movieDataSourceLiveData;
    }
}
