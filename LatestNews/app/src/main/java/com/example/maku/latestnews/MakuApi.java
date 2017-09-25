package com.example.maku.latestnews;

/**
 * Created by Maku on 2017-09-25.
 */

import retrofit2.Call;
import retrofit2.http.GET;

public interface MakuApi {

    @GET("article.rss")
    Call<RSSFeed> loadRSSFeed();
}