package com.anotherdev.stackapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.api.stackexchange.Answer;
import com.anotherdev.stackapp.api.stackexchange.Owner;
import com.anotherdev.stackapp.api.stackexchange.Question;
import com.anotherdev.stackapp.util.Dates;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.ViewHolder;
import static com.anotherdev.stackapp.adapter.QuestionAdapter.QuestionHolder;


public class AnswerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int VIEW_QUESTION = R.layout.view_question;
    private static final int VIEW_ANSWER = R.layout.view_answer;

    private final Question mQuestion;
    private final List<Answer> mAnswers;

    private final QuestionAdapter mQuestionAdapter;


    public AnswerAdapter(Question question, List<Answer> answers) {
        mQuestion = question;
        mAnswers = answers;

        mQuestionAdapter = new QuestionAdapter(Collections.singletonList(question));
    }

    @Override
    public int getItemCount() {
        return mAnswers.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_QUESTION : VIEW_ANSWER;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final boolean isQuestion = viewType == VIEW_QUESTION;
        final int layoutResId = isQuestion ? VIEW_QUESTION : VIEW_ANSWER;
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(layoutResId, parent, false);
        if (isQuestion) {
            return mQuestionAdapter.onCreateViewHolder(parent, viewType);
        } else {
            return new AnswerHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        if (vh instanceof QuestionHolder) {
            QuestionHolder qh = (QuestionHolder) vh;
            mQuestionAdapter.onBindViewHolder(qh, position);
            qh.body.setText(mQuestion.getBody());
            qh.body.setVisibility(View.VISIBLE);
            qh.divider.setVisibility(View.VISIBLE);
            qh.itemView.setOnClickListener(null);
        } else if (vh instanceof AnswerHolder) {
            AnswerHolder ah = (AnswerHolder) vh;
            final Answer a = mAnswers.get(position - 1);
            final Owner owner = a.getOwner();

            Glide.with(ah.itemView.getContext())
                    .load(owner.getProfileImage())
                    .placeholder(R.drawable.ic_stack_app_launcher)
                    .crossFade()
                    .into(ah.ownerImage);

            ah.body.setText(a.getBody());
            ah.owner.setText(owner.getDisplayName());
            ah.timeAgo.setText(Dates.getTimeAgo(a.getLastActivityDate()));
            ah.divider.setVisibility(View.VISIBLE);
        }
    }


    static class AnswerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.answer_owner_imageview) ImageView ownerImage;
        @Bind(R.id.answer_body) TextView body;
        @Bind(R.id.answer_owner) TextView owner;
        @Bind(R.id.answer_time_ago) TextView timeAgo;
        @Bind(R.id.divider) View divider;


        public AnswerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
