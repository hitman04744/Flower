package com.example.bohdan.flowerkievua.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.bohdan.flowerkievua.fragments.PlaceholderFragment;

/**
 * Created by Bohdan on 18.05.2016.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private static final String ARG_SECTION_NUMBER = "section_number";
    PlaceholderFragment fragment;
    Bundle args;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//        PlaceholderFragment.newInstance(position);
        fragment = new PlaceholderFragment();
        args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        Log.d("Fragments", fragment.toString());
        return fragment;
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position + 1) {
            case 1:
                return "Букеты";
            case 2:
                return "Цветы";
            case 3:
                return "Акссесуары";

        }
        return null;
    }

}

