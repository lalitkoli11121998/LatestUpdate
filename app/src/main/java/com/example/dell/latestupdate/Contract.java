package com.example.dell.latestupdate;

/**
 * Created by ralph on 17/02/18.
 */

public class Contract {

    public static final String DATABASE_NAME = "expenses_db";
    public static final int VERSION = 1;
    public static final String YOUTUBE_WATCH_BASE_URL = "https://www.youtube.com/watch?v=";
    public static final String YOUTUBE_THUMBNAIL_BASE_URL = "http://img.youtube.com/vi/";
    public static final String YOUTUBE_THUMBNAIL_IMAGE_QUALITY = "/hqdefault.jpg";
    public static final String IMDB_BASE_URL = "http://www.imdb.com/title/";
    public static final String movdb_id ="d3196359d679b29665024601d4aa748";
    public static final String VIEW_ALL_TV_SHOWS_TYPE = "type_view_all_tv_shows";
    public static final int AIRING_TODAY_TV_SHOWS_TYPE = 1;
    public static final int ON_THE_AIR_TV_SHOWS_TYPE = 2;
    public static final int POPULAR_TV_SHOWS_TYPE = 3;
    public static final int TOP_RATED_TV_SHOWS_TYPE = 4;
    public static final String RATING_SYMBOL = "\u2605";
    public static final String PERSON_ID = "person_id";
    public static final String QUERY = "query";
    public static final String IMAGE_LOADING_BASE_URL_342 = "https://image.tmdb.org/t/p/w342/";
    public static final String IMAGE_LOADING_BASE_URL_780 = "https://image.tmdb.org/t/p/w780/";


    static class Expenses {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "desc";
        public static final String AMOUNT = "amount";
        public static final String TABLE_NAME = "expenses";
        public static final String poster_path = "poster_path";
        public static final String overview = "overview";
        public static String vote_avarage = "vote_avarage";
    };

    static class Expenses1 {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "desc";
        public static final String AMOUNT = "amount";
        public static final String TABLE_NAME = "favourite_tv";
        public static final String poster_path = "poster_path";
        public static final String overview = "overview";
        public static String vote_avarage = "vote_avarage";

    };

}
