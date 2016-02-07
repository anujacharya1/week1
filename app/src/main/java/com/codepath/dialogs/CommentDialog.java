package com.codepath.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.codepath.week1.R;

/**
 * Created by anujacharya on 2/5/16.
 */
public class CommentDialog extends DialogFragment {


    public static String TAG = "COMMENT_DIALOG";

    public CommentDialog(){

    }
    public static CommentDialog newInstance() {
        CommentDialog frag = new CommentDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        setRetainInstance(true);
        final View view = inflater.inflate(R.layout.comment_main, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

}
