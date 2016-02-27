package com.schibsted.recipe.presenter;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.api.ApiResponse;

import rx.Observable;

public class RecipePresenter extends Presenter {

    public Observable getRecipe() {
        return observe(new GetRecipe());
    }

    private class GetRecipe implements Executor {

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
}
