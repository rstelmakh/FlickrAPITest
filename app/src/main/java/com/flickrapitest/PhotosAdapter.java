package com.flickrapitest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.flickrapitest.network.entities.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by roman on 1/21/2015.
 */
public class PhotosAdapter extends BaseAdapter {

    private List<Photo> photos;
    private LayoutInflater inflater;
    private int imageHeight;
    private int imageWidth;

    public PhotosAdapter(Context context, List<Photo> photos){
        this.inflater = LayoutInflater.from(context);
        this.photos = photos;
        this.imageHeight = (int) (360*context.getResources().getDisplayMetrics().density);
        this.imageWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.photo_list_item, parent, false);

            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Photo photo = (Photo) getItem(position);
        Picasso.with(inflater.getContext()).load(photo.getUrl()).resize(imageWidth, imageHeight).centerCrop().into(holder.imageView);
        holder.textTitle.setText(photo.getTitle());

        return convertView;
    }

    private static class ViewHolder{
        ImageView imageView;
        TextView textTitle;
    }
}
