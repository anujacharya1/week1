package com.codepath.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.models.Comment;
import com.codepath.week1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anujacharya on 2/6/16.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, ArrayList<Comment> comments) {
        super(context, 0, comments);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_dialog, parent, false);
        }

        ImageView commentProfilePic = (ImageView)convertView.findViewById(R.id.comment_usr_pic);
        TextView commentTextView = (TextView) convertView.findViewById(R.id.comment_usr_text);


        Picasso.with(getContext())
                .load(comment.getProfilePic())
                .resize(50, 50)
                .centerInside()
                .into(commentProfilePic);


        commentTextView.setText(Html.fromHtml("<b><font size='1' color='#236B8E'>"
                + comment.getUsername() + "</font></b>"));
        commentTextView.append(": "+ comment.getText());

        return convertView;
    }

}