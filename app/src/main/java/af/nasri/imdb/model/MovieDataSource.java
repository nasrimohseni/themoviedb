package af.nasri.imdb.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import af.nasri.imdb.app.Constants;
import af.nasri.imdb.network.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static af.nasri.imdb.view.MainActivity.TAG;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private ApiServices apiServices;

    public MovieDataSource(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        apiServices.getPopularMovies(Constants.POPULARITY_DESC, Constants.INITIAL_PAGE).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(@NonNull Call<Popular> call, @NonNull Response<Popular> response) {
                if (response.body() != null)
                    callback.onResult(response.body().getMovies(), null, Constants.INITIAL_PAGE + 1);
            }

            @Override
            public void onFailure(@NonNull Call<Popular> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        Log.i(TAG, "loadAfter: Page: " + params.key);
        apiServices.getPopularMovies(Constants.POPULARITY_DESC, params.key + 1).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(@NonNull Call<Popular> call, @NonNull Response<Popular> response) {
                if (response.body() != null)
                    callback.onResult(response.body().getMovies(), params.key + 1);
            }

            @Override
            public void onFailure(@NonNull Call<Popular> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: ", t);
            }
        });
    }
}
