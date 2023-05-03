package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.Globals;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;
    private static RetrofitClient instance = null;
    private final RESTService restService;
    private RetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        restService = retrofit.create(RESTService.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
    public RESTService getRestService() {
        return restService;
    }
}

