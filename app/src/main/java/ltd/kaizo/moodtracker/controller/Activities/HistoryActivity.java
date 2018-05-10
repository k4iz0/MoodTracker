package ltd.kaizo.moodtracker.controller.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Collections;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Adapter.RecycleViewAdapter;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

/**
 * The  History activity.
 */
public class HistoryActivity extends AppCompatActivity {
    /**
     * The Recycler view.
     */
    private RecyclerView recyclerView;
    /**
     * The Adapter.
     */
    private RecyclerView.Adapter adapter;
    /**
     * The Layout manager.
     */
    private RecyclerView.LayoutManager layoutManager;
    /**
     * The Mood list.
     */
    private MoodList moodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //get back data from main activity
        Intent intent = getIntent();
        moodList = (MoodList) intent.getSerializableExtra("moodList");
        Collections.sort(moodList.getMoodList(), Collections.reverseOrder(moodList.compareToByDate()));
        this.configureRecycleView();
    }

    /**
     * Configure recycle view.
     */
    private void configureRecycleView() {
        recyclerView = findViewById(R.id.activity_history_recycleview);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecycleViewAdapter(moodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
