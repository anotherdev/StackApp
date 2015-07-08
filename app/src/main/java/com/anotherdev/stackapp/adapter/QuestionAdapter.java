package com.anotherdev.stackapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anotherdev.stackapp.R;
import com.anotherdev.stackapp.api.stackexchange.Owner;
import com.anotherdev.stackapp.api.stackexchange.Question;
import com.anotherdev.stackapp.intent.DetailIntent;
import com.anotherdev.stackapp.util.Dates;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private final List<Question> mQuestions;


    public QuestionAdapter(List<Question> questions) {
        mQuestions = questions;
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
        view.setOnClickListener(mOnClick);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionHolder holder, int position) {
        final Question q = mQuestions.get(position);
        final Owner owner = q.getOwner();

        Glide.with(holder.itemView.getContext())
                .load(owner.getProfileImage())
                .placeholder(R.drawable.ic_stack_app_launcher)
                .crossFade()
                .into(holder.ownerImage);

        holder.title.setText(q.getTitle());
        holder.owner.setText(owner.getDisplayName());
        holder.timeAgo.setText(Dates.getTimeAgo(q.getLastActivityDate()));

        final boolean isLastPosition = position == (getItemCount() - 1);
        holder.divider.setVisibility(isLastPosition ? View.GONE : View.VISIBLE);

        holder.itemView.setTag(q);
    }


    private final View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Question q = (Question) v.getTag();
            DetailIntent intent = new DetailIntent(context, q);
            context.startActivity(intent);
        }
    };


    static class QuestionHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.question_owner_imageview) ImageView ownerImage;
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
