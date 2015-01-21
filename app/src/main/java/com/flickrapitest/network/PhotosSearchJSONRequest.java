package com.flickrapitest.network;

import android.util.Log;

import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class PhotosSearchJSONRequest
    extends JsonObjectRequest
{
    private static final String URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=694a3addb280f63d289542a312c0b812&format=json&nojsoncallback=1&tags=%s&extras=url_z,tags";

    private static final int MY_SOCKET_TIMEOUT_MS = 30000;

    public PhotosSearchJSONRequest(String tags, Listener<JSONObject> listener,ErrorListener errorListener)
    {
        super(Method.GET, String.format(URL, tags), null, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setTag(PhotosSearchJSONRequest.class.getName());

        Log.e(PhotosSearchJSONRequest.class.getName(), "url : " + getUrl());
    }

    @Override
    public int getMethod()
    {
        return Method.GET;
    }
}
