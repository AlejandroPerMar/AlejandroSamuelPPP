package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.Globals;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final Retrofit retrofit;
    private static RetrofitClient instance = null;
    private final RESTService restService;
    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.API_STUDYCIRCLE_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
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

