package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.buscar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentBuscarBinding;
public class BuscarFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView resultsRecyclerView;
    private ResultsAdapter resultsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        searchView = view.findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.search_hint));

        resultsRecyclerView = view.findViewById(R.id.results_recyclerview);

        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        resultsAdapter = new ResultsAdapter();
        resultsRecyclerView.setAdapter(resultsAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resultsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

    private class ResultsAdapter extends RecyclerView.Adapter<ResultsViewHolder> implements Filterable {

        private final List<BuscarFragment.Result> resultList;
        private List<BuscarFragment.Result> resultListFiltered;

        public ResultsAdapter() {
            resultList = new ArrayList<>();
            resultListFiltered = new ArrayList<>();

            resultList.add(new BuscarFragment.Result("Result 1", "Description 1", "Author 1"));
            resultList.add(new BuscarFragment.Result("Result 2", "Description 2", "Author 2"));
            resultList.add(new BuscarFragment.Result("Result 4", "Description 1", "Author 1"));
            resultList.add(new BuscarFragment.Result("Result 6", "Description 3", "Author 3"));
            resultList.add(new BuscarFragment.Result("Result 8", "Description 2", "Author 2"));
            resultList.add(new BuscarFragment.Result("Result 9", "Description 3", "Author 3"));
            resultList.add(new BuscarFragment.Result("Result 11", "Description 2", "Author 2"));
            resultList.add(new BuscarFragment.Result("Result 12", "Description 3", "Author 3"));
            resultList.add(new BuscarFragment.Result("Result 14", "Description 2", "Author 2"));
            resultList.add(new BuscarFragment.Result("Result 16", "Description 1", "Author 1"));
            resultList.add(new BuscarFragment.Result("Result 18", "Description 3", "Author 3"));
            resultList.add(new BuscarFragment.Result("Result 19", "Description 1", "Author 1"));
            resultList.add(new BuscarFragment.Result("Result 30", "Description 3", "Author 3"));
            resultList.add(new BuscarFragment.Result("Result 40", "Description 1", "Author 1"));
            resultList.add(new BuscarFragment.Result("Result 50", "Description 2", "Author 2"));
            resultList.add(new BuscarFragment.Result("Result 61", "Description 3", "Author 3"));



            resultListFiltered.addAll(resultList);
        }

        @NonNull
        @Override
        public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item_layout, parent, false);
            return new ResultsViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
            BuscarFragment.Result result = resultListFiltered.get(position);
            holder.titleTextView.setText(result.getTitle());
            holder.descriptionTextView.setText(result.getDescription());
            holder.authorTextView.setText(result.getAuthor());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(result);
                }
            });
        }

        private void showDialog(BuscarFragment.Result result) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(result.getTitle())
                    .setMessage(result.getDescription())
                    .setPositiveButton("Agregar contacto", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Acción al hacer clic en el botón 1
                        }
                    })
                    .setNegativeButton("Ver perfil", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Acción al hacer clic en el botón 2
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


        @Override
        public int getItemCount() {
            return resultListFiltered.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();

                    if (constraint == null || constraint.length() == 0) {
                        results.values = resultList;
                        results.count = resultList.size();
                    } else {
                        List<BuscarFragment.Result> filteredList = new ArrayList<>();
                        for (BuscarFragment.Result result : resultList) {
                            if (result.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                                filteredList.add(result);
                            }
                        }
                        results.values = filteredList;
                        results.count = filteredList.size();
                    }

                    return results;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    resultListFiltered = (List<BuscarFragment.Result>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }

    private static class ResultsViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView descriptionTextView;
        TextView authorTextView;

        public ResultsViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
            authorTextView = itemView.findViewById(R.id.author_textview);
        }
    }

    private static class Result {

        private final String title;
        private final String description;
        private final String author;

        public Result(String title, String description, String author) {
            this.title = title;
            this.description = description;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getAuthor() {
            return author;
        }
    }
}