package com.schibsted.recipe.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.schibsted.recipe.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void overridePendingTransitionIn() {
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (shouldAnimateOut())
            overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract boolean shouldAnimateOut();
}
