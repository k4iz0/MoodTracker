package ltd.kaizo.moodtracker.controller.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Adapter.SwipeDetector;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

public class MainActivity extends AppCompatActivity {

    private MoodItem[] picturelist = new MoodItem[5];
    private ImageButton historyBtn;
    private ImageButton commentBtn;
    private ImageView smiley;
    private EditText commentEditText;
    private RelativeLayout mainActivityLayout;
    private SwipeDetector swipeGesture;
    private SharedPreferences sharedPreferences;
    private MoodItem currentMood;
    private String comment;
    private int index;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureView();
        this.configurelist();
        this.checkDailyMood();

        this.configureHistoryBtn();
        this.configureCommentBtn();

    }

    //assign picture and background color into arraylist
    private void configurelist() {

        picturelist[0] = new MoodItem(R.color.faded_red, R.drawable.smiley_sad);
        picturelist[1] = new MoodItem(R.color.warm_grey, R.drawable.smiley_disappointed);
        picturelist[2] = new MoodItem(R.color.cornflower_blue_65, R.drawable.smiley_normal);
        picturelist[3] = new MoodItem(R.color.light_sage, R.drawable.smiley_happy);
        picturelist[4] = new MoodItem(R.color.banana_yellow, R.drawable.smiley_super_happy);
    }

    //serialize ,  link widget & initialize variable
    private void configureView() {
        //views
        historyBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        commentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        smiley = (ImageView) findViewById(R.id.activity_main_smiley);
        mainActivityLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);

        //sharePreference
        sharedPreferences = getPreferences(MODE_PRIVATE);
        //swipe initialization
        swipeGesture = new SwipeDetector(MainActivity.this);

    }


    private void configureHistoryBtn() {
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start history activity on click
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);
            }
        });
    }

    private void configureCommentBtn() {
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a dialog box to record comments
                openDialog();
            }
        });
    }

    public void onSwipeUp() {
        index--;
        setMood(index);
    }

    public void onSwipeDown() {
        index++;
        setMood(index);

    }

    //method to set mood picture and background
    private void setMood(int index) {
        //check if index is in range
        if (index < 0) {
            index = 0;
        } else if (index >picturelist.length-1) {
            index = picturelist.length - 1;
        }
        mainActivityLayout.setBackgroundResource(picturelist[index].getMoodColor());
        smiley.setImageResource(picturelist[index].getImageRessource());

    }

    //method to configure AlertDialog on comment btn
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.layout_dialog)
                .setTitle("Comment")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //end alertdialog if "cancel" is pressed
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save comment  into sharePreference
                        Dialog d = (Dialog) dialog;
                        commentEditText = (EditText) d.findViewById(R.id.activity_main_dialog_comment);
                        comment = commentEditText.getText().toString();
                        currentMood.setComment(comment);
                        Toast.makeText(MainActivity.this, "comment saved !", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void checkDailyMood() {
        String json = sharedPreferences.getString("currentMood", null);
        //get mood of the day if there's one
        if (json != null) {
            currentMood = gson.fromJson(json, MoodItem.class);
            for (int i=0; i<picturelist.length;i++) {
                if (picturelist[i].getMoodColor() == currentMood.getMoodColor()) {
                    setMood(i);
                }

            }
        } else {
            //set current mood
            currentMood = new MoodItem(picturelist[3].getImageRessource(), picturelist[3].getMoodColor(),"");
            setMood(3);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //save currentMood
        currentMood.setImageRessource(picturelist[index].getImageRessource());
        currentMood.setMoodColor(picturelist[index].getMoodColor());
        //serialize currentmood
        sharedPreferences.edit().putString("currentMood", gson.toJson(currentMood)).apply();
        }


    //method to route touchevent to swipedetector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeGesture.onTouchEvent(event);
    }

}