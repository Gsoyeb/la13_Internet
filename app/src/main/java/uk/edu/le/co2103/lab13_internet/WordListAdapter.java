package uk.edu.le.co2103.lab13_internet;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {
    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback){
        super(diffCallback);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord());
    }
}
