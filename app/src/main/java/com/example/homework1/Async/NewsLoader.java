package com.example.homework1.Async;

import android.os.AsyncTask;
import android.util.Log;


import com.example.homework1.Entity.News;
import com.example.homework1.Utils.JSONUtils;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsLoader extends AsyncTask<String, Void, ArrayList<News>> {

    public interface Callback{
        void loadNews(ArrayList<News> data);
    }

    private Callback callback;

    public NewsLoader(Callback callback){
        this.callback = callback;
    }

    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        String data = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        try{
            Response response = client.newCall(request).execute();
            data = response.body().string();
            return (ArrayList<News>) JSONUtils.parseToNews(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<News> s) {
        super.onPostExecute(s);
        if(callback != null){
            callback.loadNews(s);
        }
    }
}
