package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios.anuncio.Anuncio;

public class FormularioAnuncio extends DialogFragment {

    private OnGuardarClickListener onGuardarClickListener;

    public interface OnGuardarClickListener {
        void onGuardarClick(Anuncio anuncio);
    }

    public void setOnGuardarClickListener(OnGuardarClickListener listener) {
        this.onGuardarClickListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_formulario_anuncio, null);

        // Configurar los elementos del formulario
        EditText tituloEditText = view.findViewById(R.id.edit_text_titulo);
        EditText descripcionEditText = view.findViewById(R.id.edit_text_descripcion);
        EditText autorEditText = view.findViewById(R.id.edit_text_autor);
        Button guardarButton = view.findViewById(R.id.btn_guardar);

        // Configurar el botón de guardar
        guardarButton.setOnClickListener(v -> {
            String titulo = tituloEditText.getText().toString();
            String descripcion = descripcionEditText.getText().toString();
            String autor = autorEditText.getText().toString();

            Anuncio anuncio = new Anuncio(titulo, descripcion, autor);
            if (onGuardarClickListener != null) {
                onGuardarClickListener.onGuardarClick(anuncio);
            }

            dismiss();
        });

        builder.setView(view);
        return builder.create();
    }
}