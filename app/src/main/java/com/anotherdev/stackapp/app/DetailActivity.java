package com.anotherdev.stackapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.anotherdev.stackapp.R;

public class DetailActivity extends StackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (android.R.id.home == itemId) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
