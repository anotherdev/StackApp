package com.anotherdev.stackapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.adapter.QuestionAdapter;
import com.anotherdev.stackapp.api.stackexchange.Questions;
import com.anotherdev.stackapp.api.stackexchange.StackError;
import com.anotherdev.stackapp.api.stackexchange.StackOverflowApi;
import com.anotherdev.stackapp.rx.RxRealmCache;
import com.anotherdev.stackapp.util.Logger;

import javax.inject.Inject;

import butterknife.Bind;
import retrofit.RetrofitError;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

public class HomeActivity extends StackActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Inject StackOverflowApi mStackOverflow;

    @Bind(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;

    private MenuItem mSearchMenu;
    private SearchView mSearchView;
    private String mSearchText = "Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        search(mSearchText);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onInjectComponent(@NonNull StackApp app) {
        app.getApiComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (!mSearchView.isIconified()) {
            mSearchMenu.collapseActionView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        mSearchMenu = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchMenu);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchText = query;
                search(mSearchText);
                setTitle(mSearchText);
                mSearchMenu.collapseActionView();
                return false;
            }
        });
        return true;
    }

    private void search(final String text) {
        setRefreshing(true);

        Observable<Questions> questions;
        if (TextUtils.isEmpty(text)) {
            questions = mStackOverflow.questions();
        } else {
            questions = mStackOverflow.search(text);
        }

        questions.observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, Questions>() {
                    @Override
                    public Questions call(Throwable t) {
                        showError(t);
                        return new Questions();
                    }
                })
                .compose(RxRealmCache.<Questions>cache(this, Questions.class))
                .subscribe(
                        new Action1<Questions>() {
                            @Override
                            public void call(Questions questions) {
                                updateQuestions(questions);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                showError(t);
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                setRefreshing(false);
                            }
                        });
    }

    private void setRefreshing(final boolean refreshing) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }

    private void updateQuestions(Questions questions) {
        QuestionAdapter adapter = new QuestionAdapter(questions.getQuestions());
        mRecyclerView.setAdapter(adapter);
    }

    private void showError(Throwable t) {
        setRefreshing(false);
        Logger.e(TAG, "", t);
        String msg = t.getMessage();
        if (t instanceof RetrofitError) {
            RetrofitError re = (RetrofitError) t;
            StackError se = (StackError) re.getBodyAs(StackError.class);
            StringBuilder sb = new StringBuilder();
            sb.append(se.getId())
                    .append(" ")
                    .append(se.getName())
                    .append("\n")
                    .append(se.getMessage());
            msg = sb.toString();
        }
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG)
                .show();
    }


    private final OnRefreshListener mOnRefreshListener = new OnRefreshListener() {

        @Override
        public void onRefresh() {
            search(mSearchText);
        }
    };
}
