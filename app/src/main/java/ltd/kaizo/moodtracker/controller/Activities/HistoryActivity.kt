package ltd.kaizo.moodtracker.controller.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import ltd.kaizo.moodtracker.R
import ltd.kaizo.moodtracker.controller.Adapter.RecycleViewAdapter
import ltd.kaizo.moodtracker.model.MoodItem
import ltd.kaizo.moodtracker.model.MoodList
import java.util.*

/**
 * The  History activity.
 */
class HistoryActivity : AppCompatActivity() {

    private lateinit var moodList: MoodList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        //get back data from main activity
        val intent = intent
        moodList = intent.getSerializableExtra("moodList") as MoodList
        Collections.sort<MoodItem>(moodList.getMoodList(), Collections.reverseOrder<MoodItem>(moodList.compareToByDate()))
        this.configureRecycleView()
    }

    /**
     * Configure recycle view.
     */
    private fun configureRecycleView() {
        activity_history_recycleview.setHasFixedSize(true)
        activity_history_recycleview.layoutManager = LinearLayoutManager(this)
        activity_history_recycleview.adapter = RecycleViewAdapter(moodList)
    }
}
