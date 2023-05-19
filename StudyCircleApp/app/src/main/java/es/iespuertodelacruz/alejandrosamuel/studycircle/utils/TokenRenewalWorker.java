package es.iespuertodelacruz.alejandrosamuel.studycircle.utils;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthTokenRepository;

public class TokenRenewalWorker extends Worker {

    private AuthTokenRepository authTokenRepository;
    private AuthRepository authRepository;

    public TokenRenewalWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.authTokenRepository = new AuthTokenRepository();
        this.authRepository = new AuthRepository((Application) getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        LiveData<Object> renewBearerToken = authRepository.renewBearerToken(authTokenRepository.recuperarTokenSharedPreferences(getApplicationContext()));
        Object bearerTokenValue = renewBearerToken.getValue();
        if(bearerTokenValue instanceof String) {
            String token = (String) bearerTokenValue;
            authTokenRepository.guardarTokenSharedPreferences(getApplicationContext(), token);
        }else {
            authTokenRepository.guardarTokenSharedPreferences(getApplicationContext(), null);
        }
        return Result.success();
    }
}
