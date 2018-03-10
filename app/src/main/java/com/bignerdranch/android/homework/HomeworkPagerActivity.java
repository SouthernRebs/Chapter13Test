package com.bignerdranch.android.homework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class HomeworkPagerActivity extends AppCompatActivity {

    private static final String EXTRA_HOMEWORK_ID =
            "com.bignerdranch.android.homework.homework_id";

    private ViewPager mViewPager;
    private List<Homework> mHomeworks;

    public static Intent newIntent(Context packageContext, UUID homeworkId) {
        Intent intent = new Intent(packageContext, HomeworkPagerActivity.class);
        intent.putExtra(EXTRA_HOMEWORK_ID, homeworkId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_pager);

        UUID homeworkId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_HOMEWORK_ID);

        mViewPager = (ViewPager) findViewById(R.id.homework_view_pager);

        mHomeworks = HomeworkLab.get(this).getHomeworks();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Homework homework = mHomeworks.get(position);
                return HomeworkFragment.newInstance(homework.getId());
            }

            @Override
            public int getCount() {
                return mHomeworks.size();
            }
        });

        for (int i = 0; i < mHomeworks.size(); i++) {
            if (mHomeworks.get(i).getId().equals(homeworkId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
