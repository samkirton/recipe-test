package com.schibsted.recipe.executor;

import com.schibsted.recipe.api.ApiResponse;

public interface Executor {
    ApiResponse go();
}