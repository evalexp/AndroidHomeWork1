package com.example.homework1.Async;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.homework1.Utils.ImageConverter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

    public interface Callback{
        void loadImage(Bitmap bitmap);
    }

    private Callback callback;

    public ImageLoader(Callback callback){
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        try{
            Response response = client.newCall(request).execute();
            byte[] imageData = response.body().bytes();
            bitmap = ImageConverter.convertBytesToBitMap(imageData, 400, 500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(callback!=null){
            callback.loadImage(bitmap);
        }
    }
}
