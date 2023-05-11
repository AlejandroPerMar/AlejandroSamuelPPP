package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StartViewModel homeViewModel =
                new ViewModelProvider(this).get(StartViewModel.class);

        binding = FragmentStartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showCardViewDialog(
                            binding.txtCurso.getText().toString(),
                            binding.txtMateria.getText().toString(),
                            binding.txtTutor.getText().toString()
                    );
                }
        });

        return root;
    }

    public void showCardViewDialog(String title, String description, String author) {
        // Crea una instancia de un objeto Dialog
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_curso_layout);

        // Vincula los elementos de la vista del diálogo a variables en la clase
        TextView titleTextView = dialog.findViewById(R.id.txtCurso);
        TextView descriptionTextView = dialog.findViewById(R.id.txtMateria);
        TextView authorTextView = dialog.findViewById(R.id.txtTutor);

        // Establece el texto de los elementos en el diálogo con los datos de la CardView
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        authorTextView.setText(author);

        // Muestra el diálogo
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}