package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios.anuncio.Anuncio;

public class FormularioAnuncio extends DialogFragment {

    private OnGuardarClickListener onGuardarClickListener;
    private Spinner spinnerMotivo;
    private Spinner spinnerMateria;

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

        EditText tituloEditText = view.findViewById(R.id.edit_text_titulo);
        EditText descripcionEditText = view.findViewById(R.id.edit_text_descripcion);
        EditText autorEditText = view.findViewById(R.id.edit_text_autor);
        Button guardarButton = view.findViewById(R.id.btn_guardar);
        spinnerMotivo = view.findViewById(R.id.spinner_motivo);
        spinnerMateria = view.findViewById(R.id.spinner_materia);

        ArrayAdapter<CharSequence> motivoAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.motivo_options, android.R.layout.simple_spinner_item);
        motivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMotivo.setAdapter(motivoAdapter);

        ArrayAdapter<CharSequence> materiaAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.materia_options, android.R.layout.simple_spinner_item);
        materiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMateria.setAdapter(materiaAdapter);

        guardarButton.setOnClickListener(v -> {
            String titulo = tituloEditText.getText().toString();
            String descripcion = descripcionEditText.getText().toString();
            String autor = autorEditText.getText().toString();
            String motivo = spinnerMotivo.getSelectedItem().toString();
            String materia = spinnerMateria.getSelectedItem().toString();

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