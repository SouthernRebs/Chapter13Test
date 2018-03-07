package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HwRepository {
    private static HwRepository sHwRepository;

    private List<HwItem> mHwItems;

    public static HwRepository get(Context context) {
        if (sHwRepository == null) {
            sHwRepository = new HwRepository(context);
        }

        return sHwRepository;
    }

    private HwRepository(Context context) {
        mHwItems = new ArrayList<>();
    }

    public void addCrime(HwItem c) {
        mHwItems.add(c);
    }

    public List<HwItem> getCrimes() {
        return mHwItems;
    }

    public HwItem getCrime(UUID id) {
        for (HwItem hwItem : mHwItems) {
            if (hwItem.getId().equals(id)) {
                return hwItem;
            }
        }

        return null;
    }
}
