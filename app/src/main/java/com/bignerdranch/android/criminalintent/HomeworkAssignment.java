package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class HomeworkAssignment {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public HomeworkAssignment() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isCompleted() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
