package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.Globals;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;
    private static RetrofitClient instance = null;
    private RESTService restAuthService;
    private RESTService restNoAuthService;

    private RetrofitClient(String token) {
        TokenInterceptor tokenInterceptor = new TokenInterceptor(token);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor) // Añade aquí el token de autorización
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        //OkHttpClient client = new OkHttpClient.Builder()
         //       .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(authClient)
                .build();
        restAuthService = retrofit.create(RESTService.class);
        restNoAuthService = null;
    }

    private RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(authClient)
                .build();
        restNoAuthService = retrofit.create(RESTService.class);
        restAuthService = null;
    }

    public static synchronized RetrofitClient getInstance(String token) {
        if (Objects.isNull(instance)) {
            instance = new RetrofitClient(token);
        }
        return Objects.isNull(instance.restAuthService) ? new RetrofitClient(token) : instance;
    }

    public static synchronized RetrofitClient getInstance() {
        if (Objects.isNull(instance)) {
            instance = new RetrofitClient();
        }
        return Objects.isNull(instance.restNoAuthService) ? new RetrofitClient() : instance;
    }

    public RESTService getAuthRestService() {
        return restAuthService;
    }

    public RESTService getNoAuthRestService() {
        return restNoAuthService;
    }
}

