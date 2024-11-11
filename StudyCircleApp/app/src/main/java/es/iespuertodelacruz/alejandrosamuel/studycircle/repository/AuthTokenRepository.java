package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthTokenRepository {

    private final String nombreSharedReferences = "shared_references_encoded_token";

    public void guardarTokenSharedPreferences(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String recuperarTokenSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }

    public void limpiarTokenSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", null);
        editor.apply();
    }

}
