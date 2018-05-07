package ltd.kaizo.moodtracker.controller.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Adapter.RecycleViewAdapter;
import ltd.kaizo.moodtracker.model.MoodList;

/**
 * The  History activity.
 */
public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MoodList moodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //get back data from main activity
        Intent intent = getIntent();
        moodList = (MoodList) intent.getSerializableExtra("moodList");
        this.configureRecycleView();
    }

    //recycleView configuration
    private void configureRecycleView() {
        recyclerView = findViewById(R.id.activity_history_recycleview);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecycleViewAdapter(moodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
