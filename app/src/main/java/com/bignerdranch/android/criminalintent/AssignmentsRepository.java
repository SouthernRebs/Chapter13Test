package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentsRepository {
    private static AssignmentsRepository sCrimeLab;

    private List<HomeworkAssignment> mCrimes;

    public static AssignmentsRepository get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new AssignmentsRepository(context);
        }

        return sCrimeLab;
    }

    private AssignmentsRepository(Context context) {
        mCrimes = new ArrayList<>();
    }

    public void addCrime(HomeworkAssignment c) {
        mCrimes.add(c);
    }

    public List<HomeworkAssignment> getCrimes() {
        return mCrimes;
    }

    public HomeworkAssignment getCrime(UUID id) {
        for (HomeworkAssignment crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }

        return null;
    }
}
