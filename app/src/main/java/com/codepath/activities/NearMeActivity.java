package com.codepath.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.adapters.StreamAdapter;
import com.codepath.impl.InstagramContentProvider;
import com.codepath.interfaces.IContentProvider;
import com.codepath.models.InstagramResponse;
import com.codepath.week1.R;

import java.util.ArrayList;
import java.util.List;


public class NearMeActivity extends AppCompatActivity {

//    private StreamAdapter streamAdapter;
    private ArrayList<InstagramResponse> instagramResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup adapter
        instagramResponses = new ArrayList<>();

        StreamAdapter streamAdapter = new StreamAdapter(this, instagramResponses);

        ListView listView = (ListView) findViewById(R.id.stream);
        listView.setAdapter(streamAdapter);

        getNearByImages(streamAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<InstagramResponse> getNearByImages(ArrayAdapter streamAdapter){
        IContentProvider contentProvider = new InstagramContentProvider();
        return contentProvider.getPopularImages(streamAdapter);
    }
}
