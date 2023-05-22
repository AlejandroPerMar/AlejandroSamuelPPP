package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios.anuncio.Anuncio;

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
        adapter = new AnunciosAdapter(items,getContext());
        recyclerView.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.btn_create);
        addButton.setOnClickListener(v -> showFormularioModal());

        return view;
    }

    private void showFormularioModal() {
        FormularioAnuncio dialogFragment = new FormularioAnuncio();
        dialogFragment.setOnGuardarClickListener(new FormularioAnuncio.OnGuardarClickListener() {
            @Override
            public void onGuardarClick(Anuncio anuncio) {
                items.add(anuncio);
                adapter.notifyItemInserted(items.size() - 1);
            }
        });
        dialogFragment.show(getFragmentManager(), "fragment_formulario_anuncio");
    }
    }

class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {


    private List<Anuncio> items;
    private Context context;

    public AnunciosAdapter(List<Anuncio> items, Context context) {
        this.items = items;
        this.context = context;
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
        holder.button.setText("Ver anuncio");
        holder.button.setOnClickListener(v -> {
            mostrarDialogoAnuncio(anuncio);
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

    private void mostrarDialogoAnuncio(Anuncio anuncio) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(anuncio.getTitulo());
        builder.setMessage("Descripción: " + anuncio.getDescripcion() + "\n"
                + "Titulo: " + anuncio.getTitulo() + "\n"
                + "Materia: " + anuncio.getAutor());
        builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

