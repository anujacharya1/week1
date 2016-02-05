package com.codepath.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private android.support.v4.widget.SwipeRefreshLayout swipeContainer;

    private StreamAdapter streamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup adapter
        instagramResponses = new ArrayList<>();

        streamAdapter = new StreamAdapter(this, instagramResponses);

        ListView listView = (ListView) findViewById(R.id.stream);
        listView.setAdapter(streamAdapter);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                getNearByImages(streamAdapter, swipeContainer);
            }
        });
        getNearByImages(streamAdapter, swipeContainer);
    }

//    public void fetchTimelineAsync(int page) {
//        // Send the network request to fetch the updated data
//        // `client` here is an instance of Android Async HTTP
//        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
//            public void onSuccess(JSONArray json) {
//                // Remember to CLEAR OUT old items before appending in the new ones
//                adapter.clear();
//                // ...the data has come back, add new items to your adapter...
//                adapter.addAll(...);
//                // Now we call setRefreshing(false) to signal refresh has finished
//                swipeContainer.setRefreshing(false);
//            }
//
//            public void onFailure(Throwable e) {
//                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
//            }
//        });
//    }

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

    private List<InstagramResponse> getNearByImages(ArrayAdapter streamAdapter, SwipeRefreshLayout swipeContainer){
        IContentProvider contentProvider = new InstagramContentProvider();
        return contentProvider.getPopularImages(streamAdapter, swipeContainer);
    }
}
