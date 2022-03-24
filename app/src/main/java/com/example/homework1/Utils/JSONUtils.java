package com.example.homework1.Utils;

import com.example.homework1.Entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    public static List<News> parseToNews(String data){
        List<News> result = new ArrayList<>();
        JSONObject json = null;
        try {
            json = new JSONObject(data);
            if(json.getString("reason").equals("success!")){
                JSONObject json_result = json.getJSONObject("result");
                JSONArray json_data = json_result.getJSONArray("data");
                for(int i = 0; i < json_data.length(); i++){
                    News news = new News();
                    JSONObject item = json_data.getJSONObject(i);
                    news.setTitle(item.getString("title"));
                    news.setDate(item.getString("date"));
                    news.setAuthor(item.getString("author_name"));
                    news.setImage_url(item.getString("thumbnail_pic_s"));
                    news.setUrl(item.getString("url"));
                    news.setUniquekey(item.getString("uniquekey"));
                    result.add(news);
                }
            }
            return result;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
