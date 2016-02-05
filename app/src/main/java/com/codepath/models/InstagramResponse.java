package com.codepath.models;

import java.util.List;

/**
 * Created by anujacharya on 2/3/16.
 */
public class InstagramResponse {

    String type;
    String createdTime;
    String url;
    String profilePic;
    String username;
    Long likes;
    Caption caption;
    List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "InstagramResponse{" +
                "type='" + type + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", url='" + url + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", username='" + username + '\'' +
                ", likes=" + likes +
                ", caption=" + caption +
                ", comments=" + comments +
                '}';
    }
}
