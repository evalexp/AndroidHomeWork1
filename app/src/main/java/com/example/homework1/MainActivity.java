package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.homework1.Adapter.NewsAdapter;
import com.example.homework1.Async.NewsLoader;
import com.example.homework1.Entity.News;
import com.example.homework1.Utils.JSONUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String tag = "MainActivity";
    private List<News> newsList = new ArrayList<>();
    private String api = "https://c.m.163.com/nc/article/headline/T1348647853363/0-40.html";
    private NewsLoader newsLoader = null;
    private NewsAdapter adapter = null;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadData();
    }

    /**
     * set ListView and it's adapter
     */
    private void init(){
        adapter = new NewsAdapter(MainActivity.this, R.layout.news_item_layout, newsList);
        ListView listView = findViewById(R.id.newsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
                Log.i(tag, "exec reload func");
                loadData();
            }
        });
    }

    /**
     * async load data and put into listview
     */
    private void loadData(){
        this.newsLoader = new NewsLoader(new NewsLoader.Callback() {
            @Override
            public void loadNews(ArrayList<News> data) {
                Collections.shuffle(data);
                newsList.clear();
                for(int i = 0; i < data.size() /* && i < 10 */; i++){
                    newsList.add(data.get(i));
                }
                adapter.notifyDataSetChanged();
            }
        });
        this.newsLoader.execute(api);
    }
}