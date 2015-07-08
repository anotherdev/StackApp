package com.anotherdev.stackapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.api.stackexchange.Question;
import com.anotherdev.stackapp.api.stackexchange.Questions;
import com.anotherdev.stackapp.util.Dates;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private final List<Question> mQuestions;


    public QuestionAdapter(Questions questions) {
        mQuestions = questions.get();
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    @Override
    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_question, parent, false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionHolder holder, int position) {
        final Question q = mQuestions.get(position);

        holder.title.setText(q.getTitle());
        holder.owner.setText(q.getOwner().getDisplayName());
        holder.timeAgo.setText(Dates.getTimeAgo(q.getLastActivityDate()));

        final boolean isLastPosition = position == (getItemCount() - 1);
        holder.divider.setVisibility(isLastPosition ? View.GONE : View.VISIBLE);
    }

    static class QuestionHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.question_title) TextView title;
        @Bind(R.id.question_owner) TextView owner;
        @Bind(R.id.question_time_ago) TextView timeAgo;
        @Bind(R.id.divider) View divider;


        public QuestionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
