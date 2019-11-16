package af.nasri.imdb.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class ActivityMainViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<PagedList<Movie>> popularMovies;

    public ActivityMainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    public LiveData<PagedList<Movie>> getPopularMovies() {
        return popularMovies;
    }


}
