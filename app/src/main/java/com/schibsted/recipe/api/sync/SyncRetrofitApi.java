package com.schibsted.recipe.api.sync;

import com.schibsted.recipe.bean.Recipes;
import com.schibsted.recipe.bean.Empty;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface SyncRetrofitApi {

    @GET("/api/search")
    Response search(@Query("key") String key,@Query("q") String q,@Query("sort") String sort,@Query("page") int page);

}