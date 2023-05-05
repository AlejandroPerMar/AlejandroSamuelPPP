package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAnunciosBinding;

public class AnunciosFragment extends Fragment {

    private FragmentAnunciosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnunciosViewModel galleryViewModel =
                new ViewModelProvider(this).get(AnunciosViewModel.class);

        binding = FragmentAnunciosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}