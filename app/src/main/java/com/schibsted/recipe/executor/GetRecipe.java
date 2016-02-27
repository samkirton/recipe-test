package com.schibsted.recipe.executor;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.presenter.Presenter;

public class GetRecipe implements Executor {

    @Override
    public ApiResponse go() {
        return DefaultApplication.getInstance().getSyncApiManager().search(
                "b549c4c96152e677eb90de4604ca61a2",
                "cake",
                "r",
                0
        );
    }
}