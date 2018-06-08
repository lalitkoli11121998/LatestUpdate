package com.example.dell.latestupdate;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dell on 3/22/2018.
 */
//https://api.themoviedb.org/3/movie/popular?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1
//https://api.themoviedb.org/3/movie/upcoming?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1
//now_playing
    //https://api.themoviedb.org/3/movie/12233?api_key=d3196359d679b29665024601d4aa7482&language=en-US
    //AIzaSyCwqUccEK4ibAzw1YdKW7bWIuYJ3fWPyiw
    //AIzaSyCwqUccEK4ibAzw1YdKW7bWIuYJ3fWPyiw
    //https://api.themoviedb.org/3/movie/12234/similar?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1
    //fcgtqw
    //xjDjWPwcPU
    //AIzaSyAIn01TFLPboEPMbawCr87zdLwrDovP-Ms
    //1848229288810201
  //56bfa69192514170aa003840//credit_id
    //118545//id
    //https://api.themoviedb.org/3/person/118545?api_key=d3196359d679b29665024601d4aa7482&language=en-US
    //https://api.themoviedb.org/3/search/movie?api_key=d3196359d679b29665024601d4aa7482&language=en-US&query=padman&page=1&include_adult=false
    //https://api.themoviedb.org/3/tv/airing_today?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/333339/external_ids?api_key=d3196359d679b29665024601d4aa7482
    //faye reagan ,charley chase,vicki chase, madison lvy,kayden kross
    //https://img.youtube.com/vi/GpAuCG6iUcA/mqdefault.jpg
public interface MovieApi {

    @GET("{uname}/external_ids?api_key=d3196359d679b29665024601d4aa7482")
    Call<External> getexterids(@Path("uname") int id);

    @GET("{uname}?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie> getP(@Path("uname") String name);
    @GET("popular?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie> getPopular();

    @GET("upcoming?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie> getupcoming();

    @GET("top_rated?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie> getratedmovie();


    @GET("now_playing?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie>getNowplaying();

    @GET("{uname}?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<FindOverViews> getoverview(@Path("uname") int movieid);

    @GET("{uname}/similar?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<similarMovie> getsimilar(@Path("uname") int movieid);

    @GET("movie/{uname}/videos?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<getVideo> getvideos(@Path("uname") int movied);

    @GET("tv/{uname}/videos?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<getVideo> getvideostv(@Path("uname") int movied);


    @GET("{uname}/credits?api_key=d3196359d679b29665024601d4aa7482")
    //https://api.themoviedb.org/3/tv/{tv_id}/credits?api_key=d3196359d679b29665024601d4aa7482&language=en-US
    Call<CastMember>getCast(@Path("uname") int movieid);

    @GET("{uname}/credits?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<CastMember>getcasttv(@Path("uname") int tvid);

    @GET("person/{uname}?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<Details>getDetail(@Path("uname") int id);

    @GET("person/{uname}/movie_credits?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<moviecast>getmoviecast(@Path("uname") int id);

    @GET("person/{uname}/tv_credits?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<moviecast>getTVcast(@Path("uname") int id);

    @GET("search/movie")
    Call<similarMovie> getsearchreslut(@Query("api_key") String key, @Query("query") String Q);

    @GET("tv/airing_today?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie>getairingToday();

    @GET("tv/on_the_air?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie>getontheair();

    @GET("tv/popular?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie>getpoupularTv();

    @GET("tv/top_rated?api_key=d3196359d679b29665024601d4aa7482&language=en-US&page=1")
    Call<PopularMovie>gettopratedTv();
    @GET("{uname}?api_key=d3196359d679b29665024601d4aa7482&language=en-US")
    Call<about>getgenres(@Path("uname") int id);
}


