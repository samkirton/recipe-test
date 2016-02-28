package com.schibsted.recipe.api.sync;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SyncRetrofitApi {

    @GET("/api/search")
    Response search(@Query("key") String key, @Query("q") String q, @Query("sort") String sort, @Query("page") int page);


    @GET("/api/get")
    Response getRecipe(@Query("key") String key, @Query("rId") String rId);

}