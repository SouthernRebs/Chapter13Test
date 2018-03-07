package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HwRepository {
    private static HwRepository sHwRepository;

    private List<Homework_Assignment> mHomeworkAssignments;

    public static HwRepository get(Context context) {
        if (sHwRepository == null) {
            sHwRepository = new HwRepository(context);
        }

        return sHwRepository;
    }

    private HwRepository(Context context) {
        mHomeworkAssignments = new ArrayList<>();
    }

    public void addCrime(Homework_Assignment c) {
        mHomeworkAssignments.add(c);
    }

    public List<Homework_Assignment> getCrimes() {
        return mHomeworkAssignments;
    }

    public Homework_Assignment getCrime(UUID id) {
        for (Homework_Assignment homeworkAssignment : mHomeworkAssignments) {
            if (homeworkAssignment.getId().equals(id)) {
                return homeworkAssignment;
            }
        }

        return null;
    }
}
