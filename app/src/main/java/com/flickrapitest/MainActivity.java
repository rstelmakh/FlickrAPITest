package com.flickrapitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.flickrapitest.network.PhotoSearchEngine;
import com.flickrapitest.network.entities.Photos;


public class MainActivity extends Activity
    implements PhotoSearchEngine.OnPhotosReceivedListener{

    private PhotoSearchEngine photoSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        photoSearch = new PhotoSearchEngine(this, this);
        ((EditText)findViewById(R.id.editSearch)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s)){
                    ListView listView = (ListView) findViewById(R.id.listView);
                    listView.setAdapter(null);
                    photoSearch.search(s.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_history) {
            Intent historyIntent = new Intent(this, SearchHistoryActivity.class);
            startActivityForResult(historyIntent, SearchHistoryActivity.RESULT_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SearchHistoryActivity.RESULT_CODE
                && resultCode == RESULT_OK
                && data != null){
            String searchExtra = data.getStringExtra(SearchHistoryActivity.HISTORY_ITEM_EXTRA);
            ((EditText)findViewById(R.id.editSearch)).setText(searchExtra);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void OnPhotosReceived(Photos photos) {
        if(photos == null || photos.getTotal() == 0){
            Toast.makeText(this, R.string.no_results, Toast.LENGTH_SHORT).show();
            return;
        }
        PhotosAdapter adapter = new PhotosAdapter(this, photos.getPhotos());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void OnError(VolleyError error) {
        Toast.makeText(this, R.string.unable_access_flickr, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        photoSearch.stop();

        super.onStop();
    }
}