package af.nasri.imdb.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import af.nasri.imdb.app.Constants;
import af.nasri.imdb.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = "MovieRepository";
    private static MutableLiveData<Popular> popularMovies = new MutableLiveData<>();

    public static LiveData<Popular> getPopularMovies() {

        ApiClient.getApiService().getPopularMovies(Constants.POPULARITY_DESC).enqueue(new Callback<Popular>() {

            @Override
            public void onResponse(@NonNull Call<Popular> call, @NonNull Response<Popular> response) {
                if (response.body() != null) {
                    popularMovies.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Popular> call, @NonNull Throwable t) {
                Log.w(TAG, "onFailure: ", t);
            }

        });

        return popularMovies;
    }

}
