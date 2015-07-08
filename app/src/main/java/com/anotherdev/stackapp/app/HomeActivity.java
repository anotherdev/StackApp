package com.anotherdev.stackapp.app;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.adapter.QuestionAdapter;
import com.anotherdev.stackapp.api.ApiComponent;
import com.anotherdev.stackapp.api.DaggerApiComponent;
import com.anotherdev.stackapp.api.stackexchange.Questions;
import com.anotherdev.stackapp.api.stackexchange.StackOverflowApi;
import com.anotherdev.stackapp.rx.Actions;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

public class HomeActivity extends StackActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private StackOverflowApi mStackOverflow;

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;

    private String mSearchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiComponent apiComponent = DaggerApiComponent.create();
        mStackOverflow = apiComponent.stackoverflow();

        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        search(mSearchText);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_home;
    }

    private void search(final String text) {
        Observable<Questions> questions;
        if (TextUtils.isEmpty(text)) {
            questions = mStackOverflow.questions();
        } else {
            questions = mStackOverflow.search(text);
        }

        questions.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action1<Questions>() {
                            @Override
                            public void call(Questions questions) {
                                updateQuestions(questions);
                            }
                        },
                        mOnError,
                        new Action0() {
                            @Override
                            public void call() {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
    }

    private void updateQuestions(Questions questions) {
        QuestionAdapter adapter = new QuestionAdapter(questions);
        mRecyclerView.setAdapter(adapter);
    }


    private final OnRefreshListener mOnRefreshListener = new OnRefreshListener() {

        @Override
        public void onRefresh() {
            search(mSearchText);
        }
    };


    private final Action1<Throwable> mOnError = Actions.logError(TAG);
}
