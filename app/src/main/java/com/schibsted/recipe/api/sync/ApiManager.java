package com.schibsted.recipe.api.sync;

import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.bean.Recipes;

public interface ApiManager {
    ApiResponse<Recipes> search(String key,String q,String sort,int page);
}