package com.bignerdranch.android.homework;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeworkLab {
    private static HomeworkLab sHomeworkLab;

    private List<Homework> mHomeworks;

    public static HomeworkLab get(Context context) {
        if (sHomeworkLab == null) {
            sHomeworkLab = new HomeworkLab(context);
        }

        return sHomeworkLab;
    }

    private HomeworkLab(Context context) {
        mHomeworks = new ArrayList<>();
    }

    public void addHomework(Homework s) {
        mHomeworks.add(s);
    }

    public List<Homework> getHomeworks() {
        return mHomeworks;
    }

    public Homework getHomework(UUID id) {
        for (Homework homework : mHomeworks) {
            if (homework.getId().equals(id)) {
                return homework;
            }
        }

        return null;
    }
}
