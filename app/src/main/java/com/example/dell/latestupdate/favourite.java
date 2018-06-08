package com.example.dell.latestupdate;

/**
 * Created by dell on 4/12/2018.
 */

public class favourite {

    public String backdrop_path;
    public String original_title;
    public String character;
    public Float vote_average;
    public String name;
    public String overview;
    public int id;
    public String title;
    public String poster_path;

    public favourite(int id, String title, String poster_path   ) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;

    }


}
