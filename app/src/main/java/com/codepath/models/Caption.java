package com.codepath.models;

/**
 * Created by anujacharya on 2/4/16.
 */
public class Caption {

    String username;
    String text;
    String profilePic;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }
}
