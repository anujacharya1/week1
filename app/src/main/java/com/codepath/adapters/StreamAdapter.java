package com.codepath.adapters;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.models.InstagramResponse;
import com.codepath.week1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anujacharya on 2/5/16.
 */
public class StreamAdapter extends ArrayAdapter<InstagramResponse> {

    public StreamAdapter(Context context, ArrayList<InstagramResponse> instagramResponses) {
        super(context, 0, instagramResponses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstagramResponse instagramResponse = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_stream, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.username);
        ImageView userUploadedImg = (ImageView) convertView.findViewById(R.id.img);
        ImageView profilePic = (ImageView)convertView.findViewById(R.id.profile_pic);

        //comment
        ImageView comment1ProfilePic = (ImageView)convertView.findViewById(R.id.comment_usr1_pic);
        TextView comment1 = (TextView) convertView.findViewById(R.id.comment1);

        //time
        TextView time = (TextView) convertView.findViewById(R.id.timestamp);

        if(instagramResponse!=null){
            //username
            tvName.setText(Html.fromHtml("<strong><b><font size='3' color='#236B8E'>"+instagramResponse.getUsername()+"</font></b></strong>"));

            //image
            Picasso.with(getContext())
                    .load(instagramResponse.getUrl())
                    .resize(640, 640)
                    .into(userUploadedImg);

            //profile_pic
            Picasso.with(getContext())
                    .load(instagramResponse.getProfilePic())
                    .resize(50, 50)
                    .centerCrop()
                    .into(profilePic);


            if(instagramResponse.getComments().size()>0){
                //comment1
                Picasso.with(getContext())
                        .load(instagramResponse.getComments().get(0).getProfilePic())
                        .resize(10, 10)
                        .centerCrop()
                        .into(comment1ProfilePic);
            }
//            comment1.setText(Html.fromHtml("<b>"+instagramResponse.getComments().get(0).getUsername()+": "+"</b>")
//                                                + instagramResponse.getComments().get(0).getText());

            comment1.setText(Html.fromHtml("<b><font size='1' color='#236B8E'>"+instagramResponse.getComments().get(0).getUsername()+"</font></b>"));
            comment1.append(": "+instagramResponse.getComments().get(0).getText());


            //date
            long now = System.currentTimeMillis();
            CharSequence timeFormated =  DateUtils.getRelativeTimeSpanString(
                    Long.valueOf(instagramResponse.getCreatedTime()) * 1000, now,
                    DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_NO_NOON);
            time.setText(timeFormated.toString());
            time.setTextSize(10.0f);
        }


        //set the background of the image view


        // Return the completed view to render on screen
        return convertView;
        }
    }