package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;

public class CursoDialogFragment extends DialogFragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_MATERIA = "materia";
    private static final String ARG_TUTOR = "tutor";
    private static final String ARG_ACTIVIDADES = "actividades";

    public static CursoDialogFragment newInstance(String nombre, String materia, String tutor, List<String> actividades) {
        CursoDialogFragment fragment = new CursoDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_MATERIA, materia);
        args.putString(ARG_TUTOR, tutor);
        args.putStringArrayList(ARG_ACTIVIDADES, new ArrayList<>(actividades));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_curso_dialog, null);

        TextView nombreTextView = view.findViewById(R.id.nombreTextView);
        TextView materiaTextView = view.findViewById(R.id.materiaTextView);
        TextView tutorTextView = view.findViewById(R.id.tutorTextView);
        TextView actividadesTextView = view.findViewById(R.id.actividadesTextView);

        if (getArguments() != null) {
            String nombre = getArguments().getString(ARG_NOMBRE);
            String materia = getArguments().getString(ARG_MATERIA);
            String tutor = getArguments().getString(ARG_TUTOR);
            ArrayList<String> actividades = getArguments().getStringArrayList(ARG_ACTIVIDADES);

            nombreTextView.setText(nombre);
            materiaTextView.setText(getString(R.string.materia_label, materia));
            tutorTextView.setText(getString(R.string.tutor_label, tutor));
            actividadesTextView.setText(TextUtils.join("\n", actividades));
        }

        builder.setView(view)
                .setPositiveButton(R.string.dialog_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}