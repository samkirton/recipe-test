package com.schibsted.recipe.api.sync;

import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.api.ResponseParser;
import com.schibsted.recipe.bean.RecipeWrapper;
import com.schibsted.recipe.bean.Recipes;

import retrofit.RestAdapter;
import retrofit.client.Response;

public class SyncApiManager implements ApiManager {
    private SyncRetrofitApi mSyncRetrofitApi;
    private ResponseParser mResponseParser;

    public SyncApiManager(RestAdapter restAdapter) {
        mSyncRetrofitApi = restAdapter.create(SyncRetrofitApi.class);
        mResponseParser = new ResponseParser();
    }

    @Override
    public ApiResponse<Recipes> search(String key,String q,String sort,int page) {
        Response response = mSyncRetrofitApi.search(key,q,sort,page);
        return new ApiResponse<>(response.getStatus(), response.getHeaders(), mResponseParser.getModel(response.getBody(), Recipes.class));
    }

    @Override
    public ApiResponse<RecipeWrapper> getRecipe(String key,String rId) {
        Response response = mSyncRetrofitApi.getRecipe(key,rId);
        return new ApiResponse<>(response.getStatus(), response.getHeaders(), mResponseParser.getModel(response.getBody(), RecipeWrapper.class));
    }
}