package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private List<String> items;
    private List<String> itemsAll;

    public CustomArrayAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.items = items;
        this.itemsAll = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null) {
                    List<String> suggestions = new ArrayList<>();
                    for(String item : itemsAll) {
                        if(item.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            suggestions.add(item);
                        }
                    }

                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items.clear();
                if(results != null && results.count > 0) {
                    items.addAll((List<String>) results.values);
                }
                notifyDataSetChanged();
            }
        };
    }
}
