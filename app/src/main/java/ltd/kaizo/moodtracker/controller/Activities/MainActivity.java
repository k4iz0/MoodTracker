package ltd.kaizo.moodtracker.controller.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Adapter.SwipeDetector;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

/**
 * The  Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The Default mood.
     */
    private final int DEFAULT_MOOD = 3;
    /**
     * The Current mood key.
     */
    private final String CURRENT_MOOD_KEY = "currentMood";
    /**
     * The Mood list key.
     */
    private final String MOOD_LIST_KEY = "moodList";
    /**
     * Array of pictures link to mood color
     */
    private MoodItem[] picturelist;
    /**
     * The History btn.
     */
    private ImageButton historyBtn;
    /**
     * The Comment btn.
     */
    private ImageButton commentBtn;
    /**
     * The Smiley picture
     */
    private ImageView smiley;
    /**
     * The Main activity layout.
     */
    private RelativeLayout mainActivityLayout;
    /**
     * The Swipe gesture.
     */
    private SwipeDetector swipeGesture;
    /**
     * The Shared preferences.
     */
    private SharedPreferences sharedPreferences;
    /**
     * The Current mood.
     */
    private MoodItem currentMood;
    /**
     * The Mood list.
     */
    private MoodList moodList;
    /**
     * The Comment.
     */
    private String comment;
    /**
     * The Index of picturelist array
     */
    private int index;
    /**
     * The Gson.
     */
    private Gson gson = new Gson();
    /**
     * The Share button.
     */
    private ImageButton shareButton;

    /**
     * The Current date.
     */
    private String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //app in fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.configureView();
        this.configurelist();
        this.configureHistoryList();
        this.checkDailyMood();
        this.configureHistoryBtn();
        this.configureCommentBtn();
        this.configureShareButton();

    }

    /**
     * assign picture and background color into arraylist
     */

    private void configurelist() {
        picturelist = new MoodItem[5];
        picturelist[0] = new MoodItem(0, R.color.faded_red, R.drawable.smiley_sad);
        picturelist[1] = new MoodItem(1, R.color.warm_grey, R.drawable.smiley_disappointed);
        picturelist[2] = new MoodItem(2, R.color.cornflower_blue_65, R.drawable.smiley_normal);
        picturelist[3] = new MoodItem(3, R.color.light_sage, R.drawable.smiley_happy);
        picturelist[4] = new MoodItem(4, R.color.banana_yellow, R.drawable.smiley_super_happy);
    }

    /**
     * serialize ,  link widget & initialize variable
     */
    private void configureView() {
        //views
        historyBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        commentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        smiley = (ImageView) findViewById(R.id.activity_main_smiley);
        mainActivityLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);
        shareButton = (ImageButton) findViewById(R.id.activity_main_share_btn);
        //sharePreference
        sharedPreferences = getPreferences(MODE_PRIVATE);
        //swipe initialization
        swipeGesture = new SwipeDetector(MainActivity.this);

    }

    /**
     * initialize list of mood (moodList) by loading from sharedpreference's file
     * or setting up a new list if the file's empty
     */
    private void configureHistoryList() {
        String listMoodJson = sharedPreferences.getString(MOOD_LIST_KEY, null);
        if (listMoodJson != null) {
            moodList = gson.fromJson(listMoodJson, MoodList.class);
        } else {
            moodList = new MoodList();
        }


    }

    /**
     * set on click listener on history button
     * to start HistoryActivity with a list of mood to display
     */
    private void configureHistoryBtn() {
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save current mood
                saveCurrentMood();
                //start history activity on click if there's an history
                if (moodList.getSize() > 1) {
                    Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                    //send data to intend
                    historyActivity.putExtra(MOOD_LIST_KEY, moodList);
                    startActivity(historyActivity);
                } else {
                    Toast.makeText(MainActivity.this, R.string.moodHistoryEmpty, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * set on click listener on comment button
     * to call openDialog
     */
    private void configureCommentBtn() {
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a dialog box to record comments
                openDialog();
            }
        });
    }

    /**
     * set on click listener on share button
     */
    private void configureShareButton() {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shareSubject));
                share.putExtra(Intent.EXTRA_TEXT, setShareString());
                startActivity(Intent.createChooser(share, getString(R.string.shareVia)));
            }
        });
    }

    /**
     * function to return a String base on the mood of the day
     * for the share button function
     *
     * @return str String to send to the share's Intent
     */
    private String setShareString() {
        //i'm feeling
        String str = getString(R.string.feeling) + " ";
        switch (currentMood.getIndex()) {
            case 0:
                //sad
                str += getString(R.string.sad);
                break;
            case 1:
                //disappointed
                str += getString(R.string.disappointed);
                break;
            case 2:
                //normal
                str += getString(R.string.normal);
                break;
            case 3:
                //happy
                str += getString(R.string.happy);
                break;
            case 4:
                //great
                str += getString(R.string.great);
                break;
            default:
                //happy
                str += getString(R.string.happy);
        }
        //if there's a comment
        if (!currentMood.getComment().equalsIgnoreCase(""))
            // include it
            str += "\n " + currentMood.getComment();

        return str;
    }

    /**
     * On swipe up
     * update the mood to display
     */
    public void onSwipeUp() {
        setMood(setIndexRange("up"));
        currentMood.setComment("");
        saveCurrentMood();
    }

    /**
     * On swipe down
     * update the mood to display
     */
    public void onSwipeDown() {
        setMood(setIndexRange("down"));
        currentMood.setComment("");
        saveCurrentMood();
    }

    /**
     * function to check if the index is in range
     *
     * @param direction the direction of the swipe giving by onSwipe function (up or down)
     * @return index the mood's position in the array picturelist
     */
    private int setIndexRange(String direction) {
        if (direction.equals("up") && index < picturelist.length - 1) {
            index++;
        } else if (direction.equals("down") && index > 0) {
            index--;
        }
        return index;
    }

    /**
     * method to set mood picture and background
     *
     * @param index the mood's position in the array picturelist
     */
    private void setMood(int index) {
        mainActivityLayout.setBackgroundResource(picturelist[index].getMoodColor());
        smiley.setImageResource(picturelist[index].getImageResource());
    }

    /**
     * method to configure AlertDialog on comment btn
     */
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        final EditText commentEditText = (EditText) view.findViewById(R.id.activity_main_dialog_comment);
        if (comment != null && !comment.equals(""))
            commentEditText.setText(currentMood.getComment());
        builder.setView(view)
                .setTitle(R.string.comment)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //end alertDialog if "cancel" is pressed
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save comment  into sharePreference

                        comment = commentEditText.getText().toString();
                        currentMood.setComment(comment);
                        Toast.makeText(MainActivity.this, R.string.comment_save, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /**
     * function to check if a mood is save,
     * load it if it is, setting the current mood to default if not.
     */
    private void checkDailyMood() {
        String currentMoodJson = sharedPreferences.getString(CURRENT_MOOD_KEY, null);

        //get mood of the day if there's one
        if (currentMoodJson != null) {
            currentMood = gson.fromJson(currentMoodJson, MoodItem.class);
            if (setToday()) {
                index = currentMood.getIndex();
            } else {
                //set current mood to default if this is not of the day
                currentMood = new MoodItem(DEFAULT_MOOD, picturelist[DEFAULT_MOOD].getImageResource(), picturelist[DEFAULT_MOOD].getMoodColor(), "");
                index = DEFAULT_MOOD;
            }

        } else {
            //set current mood to default
            currentMood = new MoodItem(DEFAULT_MOOD, picturelist[DEFAULT_MOOD].getImageResource(), picturelist[DEFAULT_MOOD].getMoodColor(), "");
            index = DEFAULT_MOOD;
        }
        setMood(index);
        saveMoodToList(currentMood);

    }

    /**
     * load current mood on resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.checkDailyMood();
    }

    /**
     * save current mood on pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveCurrentMood();
    }

    /**
     * function to save the current mood (picture, background color, index and date)
     * serialize it and add to the mood list
     */
    private void saveCurrentMood() {
        //save currentMood
        currentMood.setImageResource(picturelist[index].getImageResource());
        currentMood.setMoodColor(picturelist[index].getMoodColor());
        currentMood.setIndex(index);
        currentMood.setCurrentDate();
        //serialize currentmood
        sharedPreferences.edit().putString(CURRENT_MOOD_KEY, gson.toJson(currentMood)).apply();
        saveMoodToList(currentMood);
    }

    /**
     * function to save a mood into the sharedPreferences file
     *
     * @param moodItem a MoodItem to add
     */
    private void saveMoodToList(MoodItem moodItem) {

        for (MoodItem mood : moodList.getMoodList()) {
            //if we find a mood with the same date
            if (mood.getCurrentDate().equalsIgnoreCase(moodItem.getCurrentDate())) {
                moodList.removeMood(mood);
            }
        }

        moodList.addMood(moodItem);
        sharedPreferences.edit().putString(MOOD_LIST_KEY, gson.toJson(moodList)).apply();
    }

    /**
     * function to set today's value by comparing today's date to the currentMood 's date
     *
     * @return today the bollean value of today
     */
    private Boolean setToday() {
        Date now = Calendar.getInstance().getTime();
        currentDate = new SimpleDateFormat("dd-MM-yyyy").format(now);
        //if date of the day equals the mood of the day's date
        return (currentDate.equalsIgnoreCase(currentMood.getCurrentDate()));
    }

    /**
     * method to route touchevent to swipedetector
     *
     * @param event MotionEvent
     * @return the swipeGesture onTouch method
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeGesture.onTouchEvent(event);
    }


}