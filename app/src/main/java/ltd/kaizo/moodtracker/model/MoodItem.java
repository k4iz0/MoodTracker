package ltd.kaizo.moodtracker.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MoodItem implements Serializable{
    private int mImageRessource;
    private int mMoodColor;
    private String comment;
    private String currentDate;

    public MoodItem(int moodColor, int imageRessource) {
        mImageRessource = imageRessource;
        mMoodColor = moodColor;
        setDate();
    }

    public MoodItem(int imageRessource, int moodColor, String comment) {
        mImageRessource = imageRessource;
        mMoodColor = moodColor;
        this.comment = comment;
        setDate();
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

    public String getDate() {
        return currentDate;
    }

    public void setDate() {
        Date now = Calendar.getInstance().getTime();
        this.currentDate = new SimpleDateFormat("dd-MM-yyyy").format(now);

    }

}
