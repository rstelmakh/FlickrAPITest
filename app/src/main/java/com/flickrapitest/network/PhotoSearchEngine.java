package com.flickrapitest.network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.flickrapitest.FlickrApp;
import com.flickrapitest.network.entities.PhotoSearchResponse;
import com.flickrapitest.network.entities.Photos;
import com.flickrapitest.utils.TagsUtils;

import org.json.JSONObject;

/**
 * Created by roman on 1/21/2015.
 */
public class PhotoSearchEngine
        extends VolleySuccessListener<PhotoSearchResponse,JSONObject>
        implements Response.ErrorListener{

    public interface OnPhotosReceivedListener{
        public void OnPhotosReceived(Photos photos);
        public void OnError(VolleyError error);
    }

    private OnPhotosReceivedListener onPhotosReceivedListener;
    private RequestQueue queue;

    private String lastQuery;

    @Override
    public void onResult(PhotoSearchResponse response) {
        saveLastRequest();
        if(onPhotosReceivedListener != null){
            onPhotosReceivedListener.OnPhotosReceived(response.getPhotos());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        saveLastRequest();
    }

    private void saveLastRequest(){
        if(!FlickrApp.historyList.contains(lastQuery)){
            FlickrApp.historyList.add(lastQuery);
        }
    }



    public PhotoSearchEngine(Context context, OnPhotosReceivedListener onPhotosReceivedListener){
        this.queue = Volley.newRequestQueue(context);
        this.onPhotosReceivedListener = onPhotosReceivedListener;
    }

    public void search(String tagsStr){
        stop();
        lastQuery = tagsStr.trim();
        queue.add(new PhotosSearchJSONRequest(TagsUtils.convertToTags(tagsStr), this, this));
    }

    public void stop(){
        queue.cancelAll(PhotosSearchJSONRequest.class.getName());
    }
}
