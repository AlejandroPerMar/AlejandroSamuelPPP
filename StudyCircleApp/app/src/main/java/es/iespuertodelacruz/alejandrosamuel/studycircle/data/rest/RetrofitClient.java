package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.Globals;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;
    private static RetrofitClient instance = null;
    private RESTService restAuthService;
    private RESTService restNoAuthService;

    private RetrofitClient(String token) {
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(token)) // Añade aquí el token de autorización
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(authClient)
                .build();
        restAuthService = retrofit.create(RESTService.class);
        restNoAuthService = null;
    }

    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
        return instance;
    }

    public RESTService getAuthRestService() {
        return restAuthService;
    }

    public RESTService getNoAuthRestService() {
        return restNoAuthService;
    }
}

