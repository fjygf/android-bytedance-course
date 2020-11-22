package me.yangguofeng.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        String extra = getIntent().getStringExtra("extra");
        TextView textView = findViewById(R.id.text);
        textView.setText(extra);
    }
}
