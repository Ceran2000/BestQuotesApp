package com.example.bestquotesapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestquotesapp.R;
import com.example.bestquotesapp.models.Quote;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private final List<Quote> quotesList;

    public QuotesAdapter(List<Quote> quotesList){
        this.quotesList = quotesList;
    }

    //create a view
    @NonNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quote_item, parent, false);
        return new ViewHolder(view);
    }

    //replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTvContent().setText(quotesList.get(position).getContent());
        holder.getTvAuthor().setText(quotesList.get(position).getAuthor());
        String dateAdded = "Added: " + quotesList.get(position).getDateAdded();
        holder.getTvDate().setText(dateAdded);
        StringBuilder stringBuilder = new StringBuilder("");
        for (String hashtag: quotesList.get(position).getTags()) {
            stringBuilder.append("#").append(hashtag).append(" ");
        }
        holder.getTvHashtags().setText(stringBuilder.toString().trim());
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvContent, tvAuthor, tvDate, tvHashtags;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHashtags = itemView.findViewById(R.id.tvHashtags);
        }

        public TextView getTvContent(){
            return tvContent;
        }
        public TextView getTvAuthor(){
            return tvAuthor;
        }
        public TextView getTvDate(){
            return tvDate;
        }
        public TextView getTvHashtags(){
            return tvHashtags;
        }
    }
}
