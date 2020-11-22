package me.yangguofeng.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter = new SearchAdapter();
    private SearchLayout searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        final List<String> totalData = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            totalData.add("item" + i);
        }
        searchAdapter.notifyItems(totalData);

        searchLayout = findViewById(R.id.search);
        searchLayout.setOnSearchTextChangedListener(new SearchLayout.OnSearchTextChangedListener() {
            @Override
            public void onTextChangedCallback(String text) {
                Log.d("MyActivity", "onTextChangedCallback: " + text);
                List<String> displayedData = new ArrayList<>();
                for (String item : totalData) {
                    if (item.contains(text)) {
                        displayedData.add(item);
                    }
                }
                searchAdapter.notifyItems(displayedData);
            }
        });
    }

}
