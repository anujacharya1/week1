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
import com.codepath.utils.DeviceDimensionsHelper;
import com.codepath.week1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by anujacharya on 2/5/16.
 */
public class StreamAdapter extends ArrayAdapter<InstagramResponse>{

    public StreamAdapter(Context context, ArrayList<InstagramResponse> instagramResponses) {
        super(context, 0, instagramResponses);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
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
            tvName.setText(Html.fromHtml("<strong><b><font size='3' color='#236B8E'>"
                    +instagramResponse.getUsername()+"</font></b></strong>"));

            //image
            Picasso.with(getContext())
                    .load(instagramResponse.getUrl())
                    .resize( DeviceDimensionsHelper.getDisplayWidth(getContext()), 900)
                    .into(userUploadedImg);

            //profile_pic
            Picasso.with(getContext())
                    .load(instagramResponse.getProfilePic())
                    .resize(50, 50)
                    .noFade()
                    .into(profilePic);


            if(instagramResponse.getComments().size()>0){
                //comment1
                Picasso.with(getContext())
                        .load(instagramResponse.getComments().get(0).getProfilePic())
                        .resize(20, 20)
                        .centerInside()
                        .into(comment1ProfilePic);
            }

            if(instagramResponse.getComments().size()>0){
                comment1.setText(Html.fromHtml("<b><font size='1' color='#236B8E'>"
                        +instagramResponse.getComments().get(0).getUsername()+"</font></b>"));
                comment1.append(": "+instagramResponse.getComments().get(0).getText());
            }

            //date
            long now = System.currentTimeMillis();
            CharSequence timeFormated =  DateUtils.getRelativeTimeSpanString(
                    Long.valueOf(instagramResponse.getCreatedTime()) * 1000, now,
                    DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_NO_NOON);
            time.setText(timeFormated.toString());
            time.setTextSize(10.0f);
        }
        return convertView;

        //TODO: Ask how to setup this
//        comment1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ((ListView) parent).performItemClick(v, position, 0);
//            }
//        });


        //set the background of the image view


        // Return the completed view to render on screen
        }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        long viewId = view.getId();
//
//        if (viewId == R.id.comment1) {
//            Toast.makeText(getContext(), "Button 1 clicked", Toast.LENGTH_SHORT).show();
//        }
//    }
}