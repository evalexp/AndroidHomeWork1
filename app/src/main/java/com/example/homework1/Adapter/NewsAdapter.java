package com.example.homework1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homework1.Async.ImageLoader;
import com.example.homework1.Entity.News;
import com.example.homework1.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    class ViewHolder{
        TextView title;
        TextView date;
        TextView author;
        ImageView pic;

        public ViewHolder(View view){
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            author = (TextView) view.findViewById(R.id.author);
            pic = (ImageView) view.findViewById(R.id.img);
        }
    }

    private int resourceId;

    public NewsAdapter(Context context, int listViewResourceId, List<News> newsList){
        super(context, listViewResourceId, newsList);
        resourceId = listViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        final ViewHolder holder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        News itemData = getItem(position);
        holder.title.setText(itemData.getTitle());
        holder.date.setText(itemData.getDate());
        holder.author.setText(itemData.getAuthor());

        if(!TextUtils.isEmpty(itemData.getImage_url())){
            new ImageLoader(new ImageLoader.Callback() {
                @Override
                public void loadImage(Bitmap bitmap) {
                    holder.pic.setImageBitmap(bitmap);
                }
            }).execute(itemData.getImage_url());
        }else{
            holder.pic.setVisibility(View.GONE);
        }
        return view;
    }

    private void loadNews(String URL){

    }
}
