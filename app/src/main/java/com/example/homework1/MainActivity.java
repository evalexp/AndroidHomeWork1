package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homework1.Adapter.NewsAdapter;
import com.example.homework1.Async.NewsLoader;
import com.example.homework1.Entity.News;
import com.example.homework1.Utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsAdapter adapter = new NewsAdapter(MainActivity.this, R.layout.news_item_layout, newsList);
        ListView listView = findViewById(R.id.newsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        News a = new News();
        a.setTitle("123");
        a.setAuthor("123");
        a.setDate("2022-20-2");
        newsList.add(a);
    }

}