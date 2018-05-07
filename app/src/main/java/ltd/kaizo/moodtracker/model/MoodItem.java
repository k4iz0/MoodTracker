package ltd.kaizo.moodtracker.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * The type Mood item.
 */
public class MoodItem implements Serializable {
    private int imageResource;
    private int index;
    private int moodColor;
    private String comment;
    private String currentDate;

    /**
     * Constructor who set variable and currentDate
     * use to initialize array picturelist
     *
     * @param index         index from color array picturelist
     * @param moodColor     int resource of colors from colors.xml
     * @param imageResource int resource of the smiley picture
     */
    public MoodItem(int index, int moodColor, int imageResource) {
        this.imageResource = imageResource;
        this.moodColor = moodColor;
        this.index = index;
        setCurrentDate();
    }

    /**
     * Instantiates a new Mood item with comment
     *
     * @param index         index from color array picturelist
     * @param imageResource int resource of the smiley picture
     * @param moodColor     int resource of colors from colors.xml
     * @param comment       comment save by the comment button on mainActivity
     */
    public MoodItem(int index, int imageResource, int moodColor, String comment) {
        this.imageResource = imageResource;
        this.index = index;
        this.moodColor = moodColor;
        this.comment = comment;
        setCurrentDate();
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets index.
     *
     * @param index the index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets current date.
     *
     * @return the current date
     */
    public String getCurrentDate() {
        return currentDate;
    }

    /**
     * Sets current date.
     */
    public void setCurrentDate() {
        Date now = Calendar.getInstance().getTime();
        this.currentDate = new SimpleDateFormat("dd-MM-yyyy").format(now);
    }

    /**
     * Gets mood color.
     *
     * @return the mood color
     */
    public int getMoodColor() {
        return moodColor;
    }

    /**
     * Sets mood color.
     *
     * @param moodColor the mood color
     */
    public void setMoodColor(int moodColor) {
        this.moodColor = moodColor;
    }

    /**
     * Gets image resource.
     *
     * @return the image resource
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Sets image resource.
     *
     * @param imageResource the image resource
     */
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}
