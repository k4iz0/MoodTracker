package ltd.kaizo.moodtracker.controller.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ltd.kaizo.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        this.configureRecycleView();
        Toast.makeText(this,"EN COURS D'IMPLEMENTATION",Toast.LENGTH_LONG).show();
    }

//recycleView configuration
//    private void configureRecycleView() {
//        mRecyclerView = findViewById(R.id.activity_history_recycleview);
//        mLayoutManager =  new LinearLayoutManager(this);
//        mAdapter = new RecycleViewAdapter(pictureList);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//    }
}
