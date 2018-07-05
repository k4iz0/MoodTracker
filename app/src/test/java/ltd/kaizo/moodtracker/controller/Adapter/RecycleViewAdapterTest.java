package ltd.kaizo.moodtracker.controller.Adapter;

import org.junit.Before;
import org.junit.Test;

import ltd.kaizo.moodtracker.model.MoodList;

import static org.junit.Assert.*;

public class RecycleViewAdapterTest {
    RecycleViewAdapter adapterTest;
    MoodList MoodListTest;
    @Before
    public void setUp() {
        MoodListTest = new MoodList();
        adapterTest = new RecycleViewAdapter(MoodListTest);
    }

    @Test
    public void setDiffDayTest() {
        String testStr = "02-02-1981";
        int durationTest = adapterTest.setDiffDay(testStr);
        int result = 13667;
        assertEquals(result,durationTest);
    }
}