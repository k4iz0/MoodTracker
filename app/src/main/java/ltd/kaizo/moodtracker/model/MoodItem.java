package ltd.kaizo.moodtracker.model;

import java.io.Serializable;

public class MoodItem implements Serializable{
    private int mImageRessource;
    private int mMoodColor;
    private String comment;

    public MoodItem(int moodColor, int imageRessource) {
        mImageRessource = imageRessource;
        mMoodColor = moodColor;
    }

    public MoodItem(int imageRessource, int moodColor, String comment) {
        mImageRessource = imageRessource;
        mMoodColor = moodColor;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
