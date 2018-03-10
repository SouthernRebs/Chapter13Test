package com.bignerdranch.android.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeworkListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mHomeworkRecyclerView;
    private HomeworkAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homework_list, container, false);

        mHomeworkRecyclerView = (RecyclerView) view
                .findViewById(R.id.homework_recycler_view);
        mHomeworkRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_homework_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_homework:
                Homework homework = new Homework();
                HomeworkLab.get(getActivity()).addHomework(homework);
                Intent intent = HomeworkPagerActivity
                        .newIntent(getActivity(), homework.getId());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        HomeworkLab homeworkLab = HomeworkLab.get(getActivity());
        int homeworkCount = homeworkLab.getHomeworks().size();
        String subtitle = getString(R.string.subtitle_format, homeworkCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        HomeworkLab homeworkLab = HomeworkLab.get(getActivity());
        List<Homework> homeworks = homeworkLab.getHomeworks();

        if (mAdapter == null) {
            mAdapter = new HomeworkAdapter(homeworks);
            mHomeworkRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class HomeworkHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Homework mHomework;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public HomeworkHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_homework, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.homework_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.homework_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.homework_solved);
        }

        public void bind(Homework homework) {
            mHomework = homework;
            mTitleTextView.setText(mHomework.getTitle());
            mDateTextView.setText(mHomework.getDate().toString());
            mSolvedImageView.setVisibility(homework.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Intent intent = HomeworkPagerActivity.newIntent(getActivity(), mHomework.getId());
            startActivity(intent);
        }
    }

    private class HomeworkAdapter extends RecyclerView.Adapter<HomeworkHolder> {

        private List<Homework> mHomeworks;

        public HomeworkAdapter(List<Homework> homeworks) {
            mHomeworks = homeworks;
        }

        @Override
        public HomeworkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HomeworkHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(HomeworkHolder holder, int position) {
            Homework homework = mHomeworks.get(position);
            holder.bind(homework);
        }

        @Override
        public int getItemCount() {
            return mHomeworks.size();
        }
    }
}
