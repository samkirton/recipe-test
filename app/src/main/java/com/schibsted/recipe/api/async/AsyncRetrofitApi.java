package com.schibsted.recipe.api.async;

import com.schibsted.recipe.bean.Recipes;
import com.schibsted.recipe.bean.Empty;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface AsyncRetrofitApi {

    @GET("/api/search")
    void search(@Query("key") String key, @Query("q") String q, @Query("sort") String sort, @Query("page") int page, Callback<Recipes> response);

}