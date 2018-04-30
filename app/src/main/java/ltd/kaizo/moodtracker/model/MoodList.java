package ltd.kaizo.moodtracker.model;

import java.util.ArrayList;
import java.util.List;

public class MoodList {
    private List moodList;

    public MoodList() {
        this.moodList = new ArrayList();
    }

    public void addMood(MoodItem mood) {
        this.moodList.add(mood);
    }
}
