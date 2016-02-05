package com.codepath.interfaces;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import com.codepath.models.InstagramResponse;

import java.util.List;

/**
 * Created by anujacharya on 2/3/16.
 */
public interface IContentProvider {

    final String PROVIDER = "INSTAGRAM";
    String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";

    List<InstagramResponse> getPopularImages(ArrayAdapter streamAdapter, SwipeRefreshLayout swipeContainer);
}
