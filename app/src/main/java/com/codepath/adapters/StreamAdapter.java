package com.codepath.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.codepath.models.Comment;
import com.codepath.models.InstagramResponse;
import com.codepath.utils.DeviceDimensionsHelper;
import com.codepath.week1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anujacharya on 2/5/16.
 */
public class StreamAdapter extends ArrayAdapter<InstagramResponse>{

    public StreamAdapter(Context context, ArrayList<InstagramResponse> instagramResponses) {
        super(context, 0, instagramResponses);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            // Check if an existing view is being reused, otherwise inflate the view
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_stream, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        // Get the data item for this position
        InstagramResponse instagramResponse = getItem(position);

        if(instagramResponse!=null){
            //username
            holder.tvName.setText(Html.fromHtml("<strong><b><font size='3' color='#236B8E'>"
                    +instagramResponse.getUsername()+"</font></b></strong>"));

            // hide if it's image type
            if(instagramResponse.getType().equalsIgnoreCase("image")){

                // As video is overlaying the image
                holder.userUploadedVideo.setVisibility(View.INVISIBLE);
                //image
                Picasso.with(getContext())
                        .load(instagramResponse.getUrl())
                        .resize( DeviceDimensionsHelper.getDisplayWidth(getContext()), 900)
                        .into(holder.userUploadedImg);
            }
            else{
                //video

                holder.userUploadedVideo.setVideoPath(instagramResponse.getUrl());
                MediaController mediaController = new MediaController(getContext());
                mediaController.setAnchorView(holder.userUploadedVideo);
                holder.userUploadedVideo.setMediaController(mediaController);
                holder.userUploadedVideo.requestFocus();
                holder.userUploadedVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        holder.userUploadedVideo.start();
                    }
                });
            }

            //profile_pic
            Picasso.with(getContext())
                    .load(instagramResponse.getProfilePic())
                    .resize(50, 50)
                    .noFade()
                    .into(holder.profilePic);


            if(instagramResponse.getComments().size()>0){
                    //comment1
                    Picasso.with(getContext())
                            .load(instagramResponse.getComments().get(0).getProfilePic())
                            .resize(20, 20)
                            .centerInside()
                            .into(holder.comment1ProfilePic);

                holder.comment1.setText(Html.fromHtml("<b><font size='1' color='#236B8E'>"
                            +instagramResponse.getComments().get(0).getUsername()+"</font></b>"));
                holder.comment1.append(": " + instagramResponse.getComments().get(0).getText());


                if (instagramResponse.getComments().size() > 2) {
                    Comment comment2Obj = instagramResponse.getComments().get(1);
                    if (comment2Obj != null) {
                        Picasso.with(getContext())
                                .load(comment2Obj.getProfilePic())
                                .resize(20, 20)
                                .centerInside()
                                .into(holder.comment2ProfilePic);

                        holder.comment2.setText(Html.fromHtml("<b><font size='1' color='#236B8E'>"
                                + comment2Obj.getUsername() + "</font></b>"));
                        holder.comment2.append(": " + comment2Obj.getText());
                    }
                }
            }

            //date
            long now = System.currentTimeMillis();
            CharSequence timeFormated =  DateUtils.getRelativeTimeSpanString(
                    Long.valueOf(instagramResponse.getCreatedTime()) * 1000, now,
                    DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_NO_NOON);
            holder.time.setText(timeFormated.toString());
            holder.time.setTextSize(10.0f);

            //likes

            holder.likesCount.setText(Html.fromHtml("<strong><b><font color='#b80000'>"
                    + instagramResponse.getLikes() + " likes</font></b></strong>"));
            holder.likesCount.setTextSize(10.0f);


            holder.likeBtn2.setVisibility(View.VISIBLE);
            holder.likeBtn3.setVisibility(View.INVISIBLE);

            final long incCount = instagramResponse.getLikes() + 1;
            //like activeity
            holder.likeBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.likeBtn2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.likeBtn2.setVisibility(View.INVISIBLE);
                            holder.likeBtn3.setVisibility(View.VISIBLE);


                            //change the like count
                            holder.likesCount.setText(Html.fromHtml("<strong><b><font color='#b80000'>"
                                    + incCount + " likes</font></b></strong>"));

                        }
                    }, 3);
                }
            });


        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.username)
        TextView tvName;
        @Bind(R.id.img)
        ImageView userUploadedImg;
        @Bind(R.id.video)
        VideoView userUploadedVideo;

        @Bind(R.id.profile_pic)
        ImageView profilePic;

        //comment1
        @Bind(R.id.comment_usr1_pic)
        ImageView comment1ProfilePic;
        @Bind(R.id.comment1)
        TextView comment1;

        //comment2
        @Bind(R.id.comment_usr2_pic)
        ImageView comment2ProfilePic;
        @Bind(R.id.comment2)
        TextView comment2;

        //time
        @Bind(R.id.timestamp)
        TextView time;

        @Bind(R.id.like_sym_2)
        ImageView likeBtn2;

        @Bind(R.id.like_sym_3)
        ImageView likeBtn3;

        @Bind(R.id.likes_count)
        TextView likesCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
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


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        long viewId = view.getId();
//
//        if (viewId == R.id.comment1) {
//            Toast.makeText(getContext(), "Button 1 clicked", Toast.LENGTH_SHORT).show();
//        }
//    }
}