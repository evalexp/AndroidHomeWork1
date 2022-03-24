package com.example.homework1.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

public class ImageConverter {
    public static Bitmap convertBytesToBitMap(byte[] imageData, int width, int height){
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(imageData, 0 ,imageData.length, options);
        int h = options.outHeight;
        int w = options.outWidth;
        int finalW = w / width;
        int finalH = h / height;
        int finalInSampleSize = finalH;
        if(finalW < finalH){
            finalInSampleSize = finalW;
        }
        if(finalInSampleSize <= 0){
            finalInSampleSize = 1;
        }
        options.inSampleSize = finalInSampleSize;
        bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.length, options);
        return ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
    }
}
