package ltd.kaizo.moodtracker.controller.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Adapter.CommentDialog;
import ltd.kaizo.moodtracker.model.MoodItem;

public class MainActivity extends AppCompatActivity implements CommentDialog.CommentDialogListener{

    private MoodItem[] picturelist = new MoodItem[5];
    private ImageButton historyBtn;
    private ImageButton commentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configurelist();
        this.configureView();
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

    //serialize and link widget
    private void configureView() {
        historyBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        commentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
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

    private void openDialog() {
        CommentDialog commentDialog = new CommentDialog();
        commentDialog.show(getSupportFragmentManager(), "comment dialog");
    }

    @Override
    public void applyText(String comment) {
        Toast.makeText(MainActivity.this, comment, Toast.LENGTH_LONG).show();
    }
}