package com.schibsted.recipe.presenter;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.activity.RecipeDetailsView;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.api.sync.ApiManager;
import com.schibsted.recipe.bean.RecipeWrapper;
import com.schibsted.recipe.bean.Recipes;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RecipeDetailPresenter extends Presenter<RecipeDetailsView> {
    private ApiManager mApiManager;

    public RecipeDetailPresenter(ApiManager apiManager, RecipeDetailsView recipeDetailsView) {
        super(recipeDetailsView);
        mApiManager = apiManager;
    }

    public void loadRecipe(String recipeId) {
        getView().loadingRecipe();

        observe(new GetRecipeExecutor(recipeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber(mGetRecipeAction, mGetRecipeError));
    }

    private Action1 mGetRecipeAction = new Action1<ApiResponse<RecipeWrapper>>() {

        @Override
        public void call(ApiResponse<RecipeWrapper> response) {
            if (response.getBean() != null &&
                    response.getBean().getRecipe() != null) {
                getView().displayRecipe(response.getBean().getRecipe());
            } else {
                getView().noRecipeFound();
            }
        }
    };

    private Action1 mGetRecipeError = new Action1<Throwable>() {
        @Override
        public void call(Throwable e) {
            getView().errorFetchingRecipe();
        }
    };

    private class GetRecipeExecutor implements Executor  {
        private String mRecipeId;

        public GetRecipeExecutor(String recipeId) {
            mRecipeId = recipeId;
        }

        @Override
        public ApiResponse go() {
            return mApiManager.getRecipe(
                    DefaultApplication.getInstance().getApiKey(),
                    mRecipeId
            );
        }
    };
}
