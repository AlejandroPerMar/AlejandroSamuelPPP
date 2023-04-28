package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.Globals;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;
    private static RetrofitClient instance = null;
    private final RESTService restAuthService;
    private final RESTService restNoAuthService;

    private RetrofitClient(String token) {
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor(token)) // Añade aquí el token de autorización
                .build();
        OkHttpClient noAuthClient = new OkHttpClient.Builder()
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
        if (instance == null) {
            instance = new RetrofitClient(token);
        }
        return instance;
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
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

