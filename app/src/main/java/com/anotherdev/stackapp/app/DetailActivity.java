package com.anotherdev.stackapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.adapter.AnswerAdapter;
import com.anotherdev.stackapp.api.stackexchange.Answer;
import com.anotherdev.stackapp.api.stackexchange.Question;
import com.anotherdev.stackapp.intent.DetailIntent;
import com.google.common.collect.Lists;

import butterknife.Bind;

public class DetailActivity extends StackActivity {

    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Question q = DetailIntent.getQuestion(getIntent());
        AnswerAdapter adapter = new AnswerAdapter(q, Lists.<Answer>newArrayList());
        mRecyclerView.setAdapter(adapter);
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
