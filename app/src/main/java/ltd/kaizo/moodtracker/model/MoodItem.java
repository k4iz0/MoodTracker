package ltd.kaizo.moodtracker.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MoodItem implements Serializable{
    private int imageRessource;
    private int index;
    private int moodColor;
    private String comment;
    private String currentDate;

    public MoodItem( int index, int moodColor, int imageRessource) {
        this.imageRessource = imageRessource;
        this.moodColor = moodColor;
        this.index = index;
        setCurrentDate();
    }

    public MoodItem( int index,int imageRessource, int moodColor, String comment) {
        this.imageRessource = imageRessource;
        this.index = index;
        this.moodColor = moodColor;
        this.comment = comment;
        setCurrentDate();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate() {
        Date now = Calendar.getInstance().getTime();
        this.currentDate = new SimpleDateFormat("dd-MM-yyyy").format(now);
    }

    public int getMoodColor() {
        return moodColor;
    }

    public void setMoodColor(int moodColor) {
        this.moodColor = moodColor;
    }

    public int getImageRessource() {
        return imageRessource;
    }

    public void setImageRessource(int imageRessource) {
        this.imageRessource = imageRessource;
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

    @Override
    public String toString() {
        return "MoodItem{" +
                "imageRessource=" + imageRessource +
                ", index=" + index +
                ", moodColor=" + moodColor +
                ", comment='" + comment + '\'' +
                ", currentDate='" + currentDate + '\'' +
                '}';
    }
}
