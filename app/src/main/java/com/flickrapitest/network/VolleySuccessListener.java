package com.flickrapitest.network;

import java.lang.reflect.ParameterizedType;

import android.util.Log;

import com.android.volley.Response.Listener;
import com.flickrapitest.utils.GsonUtils;

public abstract class VolleySuccessListener<T, E>
    implements Listener<E>
{
    
    public VolleySuccessListener(){}
    

    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(E response)
    {
        Log.e("VolleySuccessListener", "response : " + response);
        
         onResult((T) GsonUtils.getGson().fromJson(response.toString(),
                 (Class<T>)((ParameterizedType)getClass()
                         .getGenericSuperclass())
                         .getActualTypeArguments()[0]));
    }
    
    
    public abstract void onResult(T response);
}