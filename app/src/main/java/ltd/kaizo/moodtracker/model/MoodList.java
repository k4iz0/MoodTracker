package ltd.kaizo.moodtracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Mood list.
 */
public class MoodList implements Serializable {
    /**
     * The Mood list.
     */
    private List<MoodItem> moodList;


    /**
     * Instantiates a new Mood list by initialize an arraylist of MoodItem
     */
    public MoodList() {
        this.moodList = new ArrayList();
    }

    /**
     * Add mood.
     *
     * @param mood the mood
     */
    public void addMood(MoodItem mood) {
        this.moodList.add(mood);
    }

    /**
     * Remove mood.
     *
     * @param mood the mood
     */
    public void removeMood(MoodItem mood) {
        this.moodList.remove(mood);
    }

    /**
     * Sets mood.
     *
     * @param index the index
     * @param mood  the mood
     */
    public void setMood(int index, MoodItem mood) {
        this.moodList.set(index, mood);
    }

    /**
     * Gets mood list.
     *
     * @return the mood list
     */
    public List<MoodItem> getMoodList() {
        return moodList;
    }

    /**
     * Gets mood item.
     *
     * @param index the index
     * @return the mood item
     */
    public MoodItem getMoodItem(int index) {
        return moodList.get(index);
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return moodList.size();
    }

    /**
     * Compare to by date comparator.
     *
     * @return the comparator
     */
    public Comparator<MoodItem> compareToByDate() {
        Comparator comp = new Comparator<MoodItem>() {
            @Override
            public int compare(MoodItem o1, MoodItem o2) {
                return o1.getCurrentDate().compareTo(o2.getCurrentDate());
            }


        };
        return comp;
    }
}
