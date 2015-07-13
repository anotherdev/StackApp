package com.anotherdev.stackapp.app;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.anotherdev.stackapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class StackActivity extends AppCompatActivity {

    @Nullable @Bind(R.id.toolbar) Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int activityLayout = getActivityLayout();
        if (activityLayout > 0) {
            setContentView(activityLayout);
            ButterKnife.bind(this);

            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
                final ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    setupSupportActionBar(actionBar);
                }
            }
        }

        onInjectComponent(getApp());
    }

    @LayoutRes
    protected abstract int getActivityLayout();

    protected void setupSupportActionBar(@NonNull ActionBar actionBar) {}

    protected abstract void onInjectComponent(@NonNull StackApp app);

    @NonNull
    protected StackApp getApp() {
        return (StackApp) getApplication();
    }
}
