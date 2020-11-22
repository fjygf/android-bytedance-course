package me.yangguofeng.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<TextViewHolder> {

    @NonNull
    private List<String> itemList = new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SearchListAdapter", "OnCreateViewHolder: ");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_text, parent, false);
        return new TextViewHolder(v);
    }

    /*
    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
             .inflate(R.layout.layout_text, parent, false));
    }
    */

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        Log.d("SearchListAdapter", "OnBingViewHolder: " + position);
        holder.setItemText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("SearchListAdapter", "getItemCount: " + itemList.size());
        return itemList.size();
    }

    public void notifyItems(@NonNull List<String> itemList) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
        Log.d("SearchListAdapter", "notifyItems: " + this.itemList);
        notifyDataSetChanged();
    }
}
