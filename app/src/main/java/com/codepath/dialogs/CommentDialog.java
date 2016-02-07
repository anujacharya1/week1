package com.codepath.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.codepath.adapters.CommentsAdapter;
import com.codepath.models.Comment;
import com.codepath.models.InstagramResponse;
import com.codepath.week1.R;

import java.util.ArrayList;

/**
 * Created by anujacharya on 2/5/16.
 */
public class CommentDialog extends DialogFragment {


    InstagramResponse instagramResponse;

    public static String TAG = "COMMENT_DIALOG";

    public CommentDialog(){

    }
    public static CommentDialog newInstance(InstagramResponse instagramResponse) {
        CommentDialog frag = new CommentDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        frag.setInstagramResponse(instagramResponse);
        return frag;
    }

    private void setInstagramResponse(InstagramResponse instagramResponse) {
        this.instagramResponse = instagramResponse;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view1 = inflater.inflate(R.layout.comment_main, container, false);

        //setup adapter
        ArrayList<Comment> comments = new ArrayList<>();
        CommentsAdapter commentsAdapter = new CommentsAdapter(getActivity(), comments);

        ListView commentLstView = (ListView) view1.findViewById(R.id.comment_lv);
        commentLstView.setAdapter(commentsAdapter);

        commentsAdapter.clear();
        commentsAdapter.addAll(instagramResponse.getComments());
        commentsAdapter.notifyDataSetChanged();


        return view1;
    }

}
