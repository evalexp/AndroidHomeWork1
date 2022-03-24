package com.example.homework1.Utils;

import android.util.Log;

import com.example.homework1.Entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static String tag = "JSONUtils";

    public static List<News> parseToNews(String data){
        List<News> result = new ArrayList<>();
        JSONObject json = null;
        try {
            json = new JSONObject(data);
            JSONArray newsArray = json.getJSONArray("T1348647853363");
            for(int i = 0; i < newsArray.length(); i++){
                News news = new News();
                JSONObject item = newsArray.getJSONObject(i);
                news.setTitle(item.getString("title"));
                news.setDate(item.getString("mtime"));
                news.setAuthor(item.getString("source"));
                news.setImage_url(item.getString("imgsrc"));
                news.setUrl(item.getString("url"));
                news.setUniquekey(item.getString("sourceId"));
                result.add(news);
            }
            return result;
        }catch (JSONException e){
            Log.e(tag, e.toString());
        }
        return null;
    }
}
