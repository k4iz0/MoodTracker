package ltd.kaizo.moodtracker.model;

public class MoodItem {
    private int mImageRessource;
    private int mMoodColor;

    public MoodItem(int moodColor, int imageRessource) {
        mImageRessource = imageRessource;
        mMoodColor = moodColor;
    }

    public int getMoodColor() {
        return mMoodColor;
    }

    public void setMoodColor(int moodColor) {
        mMoodColor = moodColor;
    }

    public int getImageRessource() {
        return mImageRessource;
    }

    public void setImageRessource(int imageRessource) {
        mImageRessource = imageRessource;
    }
}
