package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class PerfilSeleccionadoRepository {

    private final String nombreSharedReferences = "shared_references_profile_selected";

    public void guardarPerfilSeleccionadoSharedPreferences(Context context, String perfil) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("perfilSeleccionado", perfil);
        editor.apply();
    }

    public String recuperarPerfilSeleccionadoSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("perfilSeleccionado", null);
    }

    public void limpiarPerfilSeleccionadoSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreSharedReferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("perfilSeleccionado", null);
        editor.apply();
    }

}
