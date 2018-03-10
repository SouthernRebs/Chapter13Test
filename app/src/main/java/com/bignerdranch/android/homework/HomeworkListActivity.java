package com.bignerdranch.android.homework;

import android.support.v4.app.Fragment;

public class HomeworkListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new HomeworkListFragment();
    }
}
