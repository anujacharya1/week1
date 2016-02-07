package com.codepath.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.adapters.CommentsAdapter;
import com.codepath.adapters.StreamAdapter;
import com.codepath.dialogs.CommentDialog;
import com.codepath.impl.InstagramContentProvider;
import com.codepath.interfaces.IContentProvider;
import com.codepath.models.Comment;
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

        final ListView listView = (ListView) findViewById(R.id.stream);
        listView.setAdapter(streamAdapter);


        // showing the comments in the adapter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setContentView(R.layout.comment_main);

                InstagramResponse instagramResponse = (InstagramResponse)listView.getAdapter().getItem(position);

                //setup adapter
                ArrayList<Comment> comments = new ArrayList<>();
                CommentsAdapter commentsAdapter = new CommentsAdapter(getApplicationContext(), comments);

                final ListView commentLstView = (ListView) findViewById(R.id.comment_lv);
                commentLstView.setAdapter(commentsAdapter);

                commentsAdapter.clear();
                commentsAdapter.addAll(instagramResponse.getComments());
                commentsAdapter.notifyDataSetChanged();

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                Fragment prev = manager.findFragmentByTag("COMMENT_DIALOG");
                if (prev != null) {
                    ft.remove(prev);
                }
                // Create and show the dialog.
                DialogFragment newFragment = CommentDialog.newInstance();
                newFragment.show(ft, "COMMENT_DIALOG");
            }
        });

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

        // get near by images during the first run
        getNearByImages(streamAdapter, swipeContainer);

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

    private List<InstagramResponse> getNearByImages(ArrayAdapter streamAdapter, SwipeRefreshLayout swipeContainer){
        IContentProvider contentProvider = new InstagramContentProvider();
        return contentProvider.getPopularImages(streamAdapter, swipeContainer);
    }
}
