package ltd.kaizo.moodtracker.controller.Activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import ltd.kaizo.moodtracker.R
import ltd.kaizo.moodtracker.controller.Adapter.SwipeDetector
import ltd.kaizo.moodtracker.model.MoodItem
import ltd.kaizo.moodtracker.model.MoodList
import java.text.SimpleDateFormat
import java.util.*

/**
 * The  Main activity.
 */
class MainActivity : AppCompatActivity(), SwipeDetector.Communicator {

    /**
     * The Default mood.
     */
    private val DEFAULT_MOOD = 3
    /**
     * The Current mood key.
     */
    private val CURRENT_MOOD_KEY = "currentMood"
    /**
     * The Mood list key.
     */
    private val MOOD_LIST_KEY = "moodList"
    /**
     * Array of pictures link to mood color
     */
    private lateinit var picturelist: Array<MoodItem>
    /**
     * The History btn.
     */
    private var historyBtn: ImageButton? = null
    /**
     * The Comment btn.
     */
    private var commentBtn: ImageButton? = null
    /**
     * The Smiley picture
     */
    private var smiley: ImageView? = null
    /**
     * The Main activity layout.
     */
    private var mainActivityLayout: RelativeLayout? = null
    /**
     * The Swipe gesture.
     */
    private var swipeGesture: SwipeDetector? = null
    /**
     * The Shared preferences.
     */
    private var sharedPreferences: SharedPreferences? = null
    /**
     * The Current mood.
     */
    private var currentMood: MoodItem? = null
    /**
     * The Mood list.
     */
    private var moodList: MoodList? = null
    /**
     * The Comment.
     */
    private var comment: String? = null
    /**
     * The Index of picturelist array
     */
    private var index: Int = 0
    /**
     * The Gson.
     */
    private val gson = Gson()
    /**
     * The Share button.
     */
    private var shareButton: ImageButton? = null

    /**
     * The Current date.
     */
    private var currentDate: String? = null

    private var gestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //app in fullscreen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        this.configureView()
        this.configurelist()
        this.configureHistoryList()
        this.checkDailyMood()
        this.configureHistoryBtn()
        this.configureCommentBtn()
        this.configureShareButton()

    }

    /**
     * assign picture and background color into array
     */

    private fun configurelist() {
        picturelist = arrayOf(MoodItem(0, R.color.faded_red, R.drawable.smiley_sad),
                MoodItem(1, R.color.warm_grey, R.drawable.smiley_disappointed),
                MoodItem(2, R.color.cornflower_blue_65, R.drawable.smiley_normal),
                MoodItem(3, R.color.light_sage, R.drawable.smiley_happy),
                MoodItem(4, R.color.banana_yellow, R.drawable.smiley_super_happy))
    }

    /**
     * serialize ,  link widget & initialize variable
     */
    private fun configureView() {
        //views
        historyBtn = activity_main_history_btn
        commentBtn = activity_main_comment_btn
        smiley = activity_main_smiley
        mainActivityLayout = activity_main_layout
        shareButton = activity_main_share_btn
        //sharePreference
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        //swipe initialization
        swipeGesture = SwipeDetector(this@MainActivity)
        gestureDetector = GestureDetector(this, swipeGesture)

    }


    /**
     * initialize list of mood (moodList) by loading from sharedpreference's file
     * or setting up a new list if the file's empty
     */
    private fun configureHistoryList() {
        val listMoodJson = sharedPreferences!!.getString(MOOD_LIST_KEY, null)
        moodList = gson.fromJson(listMoodJson, MoodList::class.java) ?: MoodList()


    }

    /**
     * set on click listener on history button
     * to start HistoryActivity with a list of mood to display
     */
    private fun configureHistoryBtn() {
        historyBtn!!.setOnClickListener {
            //save current mood
            saveCurrentMood()
            //start history activity on click if there's an history
            if (moodList!!.size > 1) {
                val historyActivity = Intent(this@MainActivity, HistoryActivity::class.java)
                //send data to intend
                historyActivity.putExtra(MOOD_LIST_KEY, moodList)
                startActivity(historyActivity)
            } else {
                Toast.makeText(this@MainActivity, R.string.moodHistoryEmpty, Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * set on click listener on comment button
     * to call openDialog
     */
    private fun configureCommentBtn() {
        commentBtn!!.setOnClickListener {
            //open a dialog box to record comments
            openDialog()
        }
    }

    /**
     * set on click listener on share button
     */
    private fun configureShareButton() {
        shareButton!!.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shareSubject))
            share.putExtra(Intent.EXTRA_TEXT, setShareString())
            startActivity(Intent.createChooser(share, getString(R.string.shareVia)))
        }
    }

    /**
     * function to return a String base on the mood of the day
     * for the share button function
     *
     * @return str String to send to the share's Intent
     */
    private fun setShareString(): String {
        //i'm feeling
        var str = getString(R.string.feeling) + " "
        when (currentMood!!.index) {
            0 ->
                //sad
                str += getString(R.string.sad)
            1 ->
                //disappointed
                str += getString(R.string.disappointed)
            2 ->
                //normal
                str += getString(R.string.normal)
            3 ->
                //happy
                str += getString(R.string.happy)
            4 ->
                //great
                str += getString(R.string.great)
            else ->
                //happy
                str += getString(R.string.happy)
        }
        //if there's a comment
        if (!currentMood!!.comment.equals("", ignoreCase = true))
        // include it
            str += "\n " + currentMood!!.comment

        return str
    }

    /**
     * On swipe up
     * update the mood to display
     */
    override fun onSwipeUp() {
        setMood(setIndexRange("up"))
        currentMood!!.comment = ""
        saveCurrentMood()
    }

    /**
     * On swipe down
     * update the mood to display
     */
    override fun onSwipeDown() {
        setMood(setIndexRange("down"))
        currentMood!!.comment = ""
        saveCurrentMood()
    }

    /**
     * function to check if the index is in range
     *
     * @param direction the direction of the swipe giving by onSwipe function (up or down)
     * @return index the mood's position in the array picturelist
     */
    private fun setIndexRange(direction: String): Int {
        if (direction == "up" && index < picturelist.size - 1) {
            index++
        } else if (direction == "down" && index > 0) {
            index--
        }
        return index
    }

    /**
     * method to set mood picture and background
     *
     * @param index the mood's position in the array picturelist
     */
    private fun setMood(index: Int) {
        mainActivityLayout!!.setBackgroundResource(picturelist[index].moodColor)
        smiley!!.setImageResource(picturelist[index].imageResource)
    }

    /**
     * method to configure AlertDialog on comment btn
     */
    private fun openDialog() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.layout_dialog, null)
      view.activity_main_dialog_comment.setText(currentMood!!.comment)
        builder.setView(view)
                .setTitle(R.string.comment)
                .setNegativeButton(android.R.string.cancel) { dialog, which ->
                    //end alertDialog if "cancel" is pressed
                }
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    //save comment  into sharePreference

                    comment = view.activity_main_dialog_comment.text.toString()
                    currentMood!!.comment = comment as String
                    Toast.makeText(this@MainActivity, R.string.comment_save, Toast.LENGTH_SHORT).show()
                }.show()
    }

    /**
     * function to check if a mood is save,
     * load it if it is, setting the current mood to default if not.
     */
    private fun checkDailyMood() {
        val currentMoodJson = sharedPreferences!!.getString(CURRENT_MOOD_KEY, null)

        //get mood of the day if there's one
        if (currentMoodJson != null) {
            currentMood = gson.fromJson(currentMoodJson, MoodItem::class.java)
            if (setToday()) {
                index = currentMood!!.index
            } else {
                //set current mood to default if this is not of the day
                currentMood = MoodItem(DEFAULT_MOOD, picturelist[DEFAULT_MOOD].imageResource, picturelist[DEFAULT_MOOD].moodColor, "")
                index = DEFAULT_MOOD
            }

        } else {
            //set current mood to default
            currentMood = MoodItem(DEFAULT_MOOD, picturelist[DEFAULT_MOOD].imageResource, picturelist[DEFAULT_MOOD].moodColor, "")
            index = DEFAULT_MOOD
        }
        setMood(index)
        saveMoodToList(currentMood!!)

    }

    /**
     * load current mood on resume
     */
    override fun onResume() {
        super.onResume()
        this.checkDailyMood()
    }

    /**
     * save current mood on pause
     */
    override fun onPause() {
        super.onPause()
        saveCurrentMood()
    }

    /**
     * function to save the current mood (picture, background color, index and date)
     * serialize it and add to the mood list
     */
    private fun saveCurrentMood() {
        //save currentMood
        currentMood!!.imageResource = picturelist[index].imageResource
        currentMood!!.moodColor = picturelist[index].moodColor
        currentMood!!.index = index
        currentMood!!.setCurrentDate()
        //serialize currentmood
        sharedPreferences!!.edit().putString(CURRENT_MOOD_KEY, gson.toJson(currentMood)).apply()
        saveMoodToList(currentMood!!)
    }

    /**
     * function to save a mood into the sharedPreferences file
     *
     * @param moodItem a MoodItem to add
     */
    private fun saveMoodToList(moodItem: MoodItem) {

        for (mood in moodList!!.getMoodList()) {
            //if we find a mood with the same date
            if (mood.currentDate!!.equals(moodItem.currentDate!!, ignoreCase = true)) {
                moodList!!.removeMood(mood)
            }
        }

        moodList!!.addMood(moodItem)
        sharedPreferences!!.edit().putString(MOOD_LIST_KEY, gson.toJson(moodList)).apply()
    }

    /**
     * function to set today's value by comparing today's date to the currentMood 's date
     *
     * @return today the bollean value of today
     */
    private fun setToday(): Boolean {
        val now = Calendar.getInstance().time
        currentDate = SimpleDateFormat("dd-MM-yyyy").format(now)
        //if date of the day equals the mood of the day's date
        return currentDate!!.equals(currentMood!!.currentDate!!, ignoreCase = true)
    }

    //    /**
    //     * method to route touchevent to swipedetector
    //     *
    //     * @param event MotionEvent
    //     * @return the swipeGesture onTouch method
    //     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector!!.onTouchEvent(event)
        //return swipeGesture.onTouchEvent(event);
    }


}