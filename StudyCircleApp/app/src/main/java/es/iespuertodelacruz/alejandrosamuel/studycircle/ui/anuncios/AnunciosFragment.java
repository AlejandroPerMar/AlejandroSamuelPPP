package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAnunciosBinding;

public class AnunciosFragment extends Fragment {

    private List<Anuncio> items;
    private AnunciosAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anuncios, container, false);

        items = new ArrayList<>();
        items.add(new Anuncio("Anuncio 1", "Descripción del anuncio 1", "Usuario 1"));
        items.add(new Anuncio("Anuncio 2", "Descripción del anuncio 2", "Usuario 2"));
        items.add(new Anuncio("Anuncio 3", "Descripción del anuncio 3", "Usuario 3"));
        items.add(new Anuncio("Anuncio 4", "Descripción del anuncio 4", "Usuario 4"));
        items.add(new Anuncio("Anuncio 5", "Descripción del anuncio 5", "Usuario 5"));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AnunciosAdapter(items);
        recyclerView.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.btn_create);
        addButton.setOnClickListener(v -> {
            items.add(new Anuncio("Nuevo anuncio", "Descripción del nuevo anuncio", "Usuario"));
            adapter.notifyItemInserted(items.size() - 1);
        });

        return view;
    }
}

 class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {

    private List<Anuncio> items;

    public AnunciosAdapter(List<Anuncio> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anuncios_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anuncio anuncio = items.get(position);
        holder.tituloTextView.setText(anuncio.getTitulo());
        holder.descripcionTextView.setText(anuncio.getDescripcion());
        holder.autorTextView.setText(anuncio.getAutor());
        holder.button.setOnClickListener(v -> {
            // handle button click
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tituloTextView;
        public TextView descripcionTextView;
        public TextView autorTextView;
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.title);
            descripcionTextView = itemView.findViewById(R.id.description);
            autorTextView = itemView.findViewById(R.id.user);
            button = itemView.findViewById(R.id.button);
        }
    }
}



class Anuncio {
    private String titulo;
    private String descripcion;
    private String autor;

    public Anuncio(String titulo, String descripcion, String autor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAutor() {
        return autor;
    }
}