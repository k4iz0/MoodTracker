package ltd.kaizo.moodtracker.controller.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.controller.Activities.HistoryActivity;
import ltd.kaizo.moodtracker.controller.Activities.MainActivity;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private MoodList smileyHistory;
    private String dateNow;

    public RecycleViewAdapter(MoodList smileyHistory) {
        this.smileyHistory = smileyHistory;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, int position) {
        final MoodItem currentItem = smileyHistory.getMoodItem(position);
        holder.itemList.setBackgroundResource(currentItem.getMoodColor());
        //if there's no comment hide the button
        if (currentItem.getComment().equalsIgnoreCase("")) {
            holder.commentBtn.setVisibility(View.INVISIBLE);

        } else {
            holder.commentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), currentItem.getComment(), Toast.LENGTH_LONG).show();
                }
            });
        }
        int diffDay = displayDateText(currentItem.getCurrentDate());
        if (diffDay == 0) {
            holder.recycleViewlayout.setVisibility(View.INVISIBLE);
        }
        String str = "";
        if (diffDay == 1) {
            holder.itemList.setText(R.string.yesterday);
        } else if (diffDay == 2) {
            holder.itemList.setText(R.string.before_yesterday);
        } else if (diffDay > 6 && diffDay <= 31) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fr")) {

                str = holder.itemList.getContext().getString(R.string.ago) + " " + holder.itemList.getContext().getString(R.string.more) + " " + holder.itemList.getContext().getString(R.string.week);
                holder.itemList.setText(str);

            } else {
                str = holder.itemList.getContext().getString(R.string.more) + " " + holder.itemList.getContext().getString(R.string.week) + " " + holder.itemList.getContext().getString(R.string.ago);
                holder.itemList.setText(str);
            }
        } else if (diffDay > 31) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fr")) {
                str = holder.itemList.getContext().getString(R.string.ago) + " " + holder.itemList.getContext().getString(R.string.more) + " " + holder.itemList.getContext().getString(R.string.month);
                holder.itemList.setText(str);

            } else {
                str = holder.itemList.getContext().getString(R.string.more) + " " + holder.itemList.getContext().getString(R.string.month) + " " + holder.itemList.getContext().getString(R.string.ago);
                holder.itemList.setText(str);
            }
        } else {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fr")) {
                str =  holder.itemList.getContext().getString(R.string.ago) + " " + diffDay + " " +  holder.itemList.getContext().getString(R.string.days);
                holder.itemList.setText(str);

            } else {
                str = diffDay + " " +  holder.itemList.getContext().getString(R.string.days) + " " +  holder.itemList.getContext().getString(R.string.ago);
                holder.itemList.setText(str);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (smileyHistory == null) {
            return 0;
        } else if (smileyHistory.getSize() > 7) {
            return 7;
        } else {
            return smileyHistory.getSize();
        }
    }

    private int displayDateText(String date) {
        Date now = Calendar.getInstance().getTime();
        dateNow = new SimpleDateFormat("dd-MM-yyyy").format(now);
        DateManager currentDate = new DateManager(dateNow);
        DateManager commentDate = new DateManager((date));
        int diffDay = currentDate.getDay() - commentDate.getDay();
        return diffDay;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView itemList;
        public ImageButton commentBtn;
        public RelativeLayout recycleViewlayout;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            itemList = (TextView) itemView.findViewById(R.id.activity_history_item_list_textview);
            commentBtn = (ImageButton) itemView.findViewById(R.id.activity_history_item_list_comment_btn);
            recycleViewlayout = (RelativeLayout) itemView.findViewById(R.id.recycleView_layout);
        }
    }

}
