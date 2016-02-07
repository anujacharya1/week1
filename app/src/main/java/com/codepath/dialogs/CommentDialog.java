package com.codepath.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by anujacharya on 2/5/16.
 */
public class CommentDialog extends DialogFragment {


    View commentMain;

    public static String TAG = "COMMENT_DIALOG";

    public CommentDialog(){

    }
    public static CommentDialog newInstance(View commentMain) {
        CommentDialog frag = new CommentDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        frag.setCommentMain(commentMain);
        return frag;
    }

    private void setCommentMain(View commentMain) {
        this.commentMain = commentMain;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return commentMain;
    }

}
