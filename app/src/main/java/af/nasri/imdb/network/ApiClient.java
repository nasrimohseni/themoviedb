package af.nasri.imdb.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import af.nasri.imdb.app.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static OkHttpClient.Builder httpClient;

    public static Retrofit getInstance() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NonNull
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter(Constants.API_KEY, Constants.API_KEY_VALUE).build();
                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder().url(url);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiEndpoints.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiServices getApiService(){
        return ApiClient.getInstance().create(ApiServices.class);
    }

}
