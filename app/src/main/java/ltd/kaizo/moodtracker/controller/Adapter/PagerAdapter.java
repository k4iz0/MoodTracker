package ltd.kaizo.moodtracker.controller.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.controller.Fragment.SmileyFragment;

public class PagerAdapter extends FragmentPagerAdapter{

    private int mImageRessource;
    private String mMoodColor;
    private MoodItem[] mood;
    public PagerAdapter(FragmentManager fm, MoodItem[] mood) {
        super(fm);
        this.mood = mood;
    }

    @Override
    public Fragment getItem(int position) {

        return (SmileyFragment.newInstance(mood[position].getMoodColor(), mood[position].getImageRessource()));
    }

    @Override
    public int getCount() {
        return 5;
    }
}
