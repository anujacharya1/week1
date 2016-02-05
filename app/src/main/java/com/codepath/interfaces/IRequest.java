package com.codepath.interfaces;

/**
 * Created by anujacharya on 2/3/16.
 */
public interface IRequest {

    public <T> T get(String url);

}
