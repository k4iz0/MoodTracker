package ltd.kaizo.moodtracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoodList implements Serializable{
    private List<MoodItem> moodList;


    public MoodList() {
        this.moodList = new ArrayList();
    }

    public void addMood(MoodItem mood) {
        this.moodList.add(mood);
    }

    public void removeMood(MoodItem mood) {
        this.moodList.remove(mood);
    }

    public void setMood(int index, MoodItem mood) {
        this.moodList.set(index, mood);
    }
    public List<MoodItem> getMoodList() {
        return moodList;
    }

    public MoodItem getMoodItem(int index) {
        return moodList.get(index);
    }

    public int getSize() {
        return moodList.size();
    }
}
