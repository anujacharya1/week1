package com.codepath.models;

/**
 * Created by anujacharya on 2/5/16.
 */
public class Comment {

    String username;
    String profilePic;
    String text;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
