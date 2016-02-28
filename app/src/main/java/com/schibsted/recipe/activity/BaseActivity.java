package com.schibsted.recipe.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.schibsted.recipe.api.ApiResponse;

import rx.Subscriber;
import rx.functions.Action1;

public class BaseActivity extends AppCompatActivity {

    protected void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
