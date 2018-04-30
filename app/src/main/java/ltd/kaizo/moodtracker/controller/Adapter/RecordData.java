package ltd.kaizo.moodtracker.controller.Adapter;

import com.google.gson.Gson;

import java.util.ArrayList;

import ltd.kaizo.moodtracker.model.MoodItem;


public class RecordData {
    private Gson gson;

    public RecordData(Gson gson) {
        this.gson = gson;
    }

    private void saveMood(MoodItem mmod) {

    }
}
//
//mUser.setScore(score);
//
//        mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
//        liste.addScore(mUser);
//        //serialisation de la liste
//        mPreferences.edit().putString("listeScore", gson.toJson(liste)).apply();