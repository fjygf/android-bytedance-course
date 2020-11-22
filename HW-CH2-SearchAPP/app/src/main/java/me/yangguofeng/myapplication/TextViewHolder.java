package me.yangguofeng.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textView;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        itemView.setOnClickListener(this);
    }

    public void setItemText(String text) {
        textView.setText(text);
        Log.d("TextViewHolder", "setItemText: " + this.textView.getText());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ItemDetail.class);
        intent.putExtra("extra", textView.getText().toString());
        v.getContext().startActivity(intent);
    }
}
