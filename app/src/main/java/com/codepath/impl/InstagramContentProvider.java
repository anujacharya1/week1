package com.codepath.impl;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.codepath.interfaces.IContentProvider;
import com.codepath.models.Caption;
import com.codepath.models.Comment;
import com.codepath.models.InstagramResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anujacharya on 2/3/16.
 */
public class InstagramContentProvider implements IContentProvider {

    private static final String URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

    public static InstagramResponse buildInstagramResponse(JSONObject data) {
        InstagramResponse instagramResponse = null;
        // Deserialize json into object fields
        try {
            if(data.getString("type").equalsIgnoreCase("image")){
                instagramResponse = new InstagramResponse();
                instagramResponse.setCreatedTime(data.getString("created_time"));
                instagramResponse.setType(data.getString("type"));


                JSONObject user = data.getJSONObject("user");
                instagramResponse.setUsername(user.getString("username"));
                instagramResponse.setProfilePic(user.getString("profile_picture"));

                JSONObject images = data.getJSONObject("images");
                JSONObject standardRes = images.getJSONObject("standard_resolution");

                instagramResponse.setUrl(standardRes.getString("url"));

                JSONObject likes = data.getJSONObject("likes");
                instagramResponse.setLikes(Long.valueOf(likes.getString("count")));


                JSONObject captionI = data.getJSONObject("caption");

                if(captionI!=null){
                    Caption caption = new Caption();

                    caption.setText(captionI.getString("text"));

                    JSONObject from = captionI.getJSONObject("from");
                    caption.setUsername(from.getString("username"));
                    caption.setProfilePic(from.getString("profile_picture"));

                    instagramResponse.setCaption(caption);
                }

                JSONObject commentI = data.getJSONObject("comments");

                JSONArray commentDatas = commentI.getJSONArray("data");

                List<Comment> comments = new ArrayList<>();

                for(int i=0; i<commentDatas.length(); i++){

                    JSONObject commentData = commentDatas.getJSONObject(i);

                    Comment comment = new Comment();
                    comment.setText(commentData.getString("text"));

                    JSONObject from = commentData.getJSONObject("from");

                    comment.setUsername(from.getString("username"));
                    comment.setProfilePic(from.getString("profile_picture"));

                    comments.add(comment);

                }
                instagramResponse.setComments(comments);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR", e.getMessage());
            return null;
        }
        return instagramResponse;
    }

    @Override
    public List<InstagramResponse> getPopularImages(final ArrayAdapter streamAdapter, final SwipeRefreshLayout swipeContainer) {
        AsyncHttpClient client = new AsyncHttpClient();
        try{
            client.get(URL, null, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray data = null;
                    List<InstagramResponse> instagramResponses = new ArrayList<>();
                    try {
                        data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject obj = data.getJSONObject(i);
                            InstagramResponse instagramResponse = buildInstagramResponse(obj);
                            if(instagramResponse!=null){
                                Log.i("INFO", instagramResponse.toString());
                                instagramResponses.add(buildInstagramResponse(obj));
                            }
                        }

                        streamAdapter.clear();
                        streamAdapter.addAll(instagramResponses);
                        streamAdapter.notifyDataSetChanged();

                        swipeContainer.setRefreshing(false);

                        Log.i("INFO", "Size is =" + instagramResponses.size());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    Log.e("ERROR", "res="+res+" statusCode="+statusCode +" message="+t.getMessage());
                }

            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
