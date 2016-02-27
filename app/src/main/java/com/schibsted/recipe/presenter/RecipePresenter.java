package com.schibsted.recipe.presenter;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.activity.RecipeView;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.bean.Recipes;
import com.schibsted.recipe.executor.GetRecipe;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RecipePresenter extends Presenter {
    private RecipeView mRecipeView;

    public RecipePresenter(RecipeView recipeView) {
        mRecipeView = recipeView;
    }

    public void loadRecipes() {
        observe(new GetRecipe())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber(mGetRecipeAction, mGetRecipeError));
    }

    private Action1 mGetRecipeAction = new Action1<ApiResponse<Recipes>>() {

        @Override
        public void call(ApiResponse<Recipes> response) {
            mRecipeView.getRecipes(response.getBean());
        }
    };

    private Action1 mGetRecipeError = new Action1<Throwable>() {
        @Override
        public void call(Throwable e) {
            mRecipeView.errorFetchingRecipes();
        }
    };
}
