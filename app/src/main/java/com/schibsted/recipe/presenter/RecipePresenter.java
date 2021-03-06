package com.schibsted.recipe.presenter;

import com.schibsted.recipe.DefaultApplication;
import com.schibsted.recipe.activity.RecipeView;
import com.schibsted.recipe.api.ApiResponse;
import com.schibsted.recipe.api.sync.ApiManager;
import com.schibsted.recipe.bean.Recipes;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RecipePresenter extends Presenter<RecipeView> {

    private ApiManager mApiManager;
    private String mLastSearchTerms = "";
    private int mCurrentPage = 1;

    public RecipePresenter(ApiManager apiManager, RecipeView recipeView) {
        super(recipeView);
        mApiManager = apiManager;
    }

    public void searchRecipes(String terms, int page) {
        getView().loadingRecipes();
        search(terms,page);
    }

    public void searchInitialRecipes() {
        getView().loadingRecipes();
        search("", 1);
    }

    public void getLastSearchedRecipes() {
        getView().loadingRecipes();
        search(mLastSearchTerms, 1);
    }

    public void getMoreRecipes() {
        mCurrentPage++;
        search(mLastSearchTerms, mCurrentPage);
    }

    private void search(String terms, int page) {
        mLastSearchTerms = terms;
        mCurrentPage = page;
        observe(new SearchExecutor(terms, page))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber(mSearchAction, mSearchError));
    }

    public String getLastSearchTerms() {
        return mLastSearchTerms;
    }

    private Action1 mSearchAction = new Action1<ApiResponse<Recipes>>() {

        @Override
        public void call(ApiResponse<Recipes> response) {
            if (response.getBean() != null &&
                    response.getBean().getRecipes() != null &&
                    response.getBean().getRecipes().length > 0) {
                if (mCurrentPage == 1) {
                    getView().displayRecipes(response.getBean().getRecipes());
                } else {
                    getView().addMoreRecipes(response.getBean().getRecipes());
                }
            } else {
                getView().noRecipesFound();
            }
        }
    };

    private Action1 mSearchError = new Action1<Throwable>() {
        @Override
        public void call(Throwable e) {
            getView().errorFetchingRecipes();
        }
    };

    private class SearchExecutor implements Executor  {
        private String mTerms;
        private int mPage;

        public SearchExecutor(String terms, int page) {
            mTerms = terms;
            mPage = page;
        }

        @Override
        public ApiResponse go() {
            final String SORT_RATING = "r";
            return mApiManager.search(
                    DefaultApplication.getInstance().getApiKey(),
                    mTerms,
                    SORT_RATING,
                    mPage
            );
        }
    };
}
