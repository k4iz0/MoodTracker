package ltd.kaizo.moodtracker.controller.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

/**
 * The type Recycle view adapter.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    /**
     * The History limit for hte recycle list
     */
    private final int HISTORY_LIMIT = 7;
    /**
     * The Smiley history.
     */
    private MoodList smileyHistory;

    /**
     * Instantiates a new Recycle view adapter
     * with an arraylist of mood
     *
     * @param smileyHistory the arraylist of mood
     */
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
        //setting up the background color
        holder.itemList.setBackgroundResource(currentItem.getMoodColor());
        //if there's no comment hide the button
        if (currentItem.getComment().equalsIgnoreCase("")) {
            holder.commentBtn.setVisibility(View.INVISIBLE);

        } else {
            holder.commentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), currentItem.getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //get the difference of day
        int diffDay = setDiffDay(currentItem.getCurrentDate());
        String str = "";
        Context context = holder.itemList.getContext();
        switch (diffDay) {
            case 0:
                //hide the mood of the day
                holder.recycleViewlayout.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
                break;
            case 1:
                str = context.getString(R.string.yesterday);
                break;
            case 2:
                str = context.getString(R.string.before_yesterday);
                break;
            default:
                str = String.format(context.getString(R.string.x_days_ago), diffDay);
                break;
        }
        holder.itemList.setText(str);

    }

    /**
     * limit the number of item to the limit fix by variable HISTORY_LIMIT
     *
     * @return size of recycleView list
     */
    @Override
    public int getItemCount() {
        if (smileyHistory == null) {
            return 0;
        } else if (smileyHistory.getSize() > HISTORY_LIMIT) {
            return HISTORY_LIMIT;
        } else {
            return smileyHistory.getSize();
        }
    }

    /**
     * soustract the date of the item to the current date to return a number of days
     *
     * @param date simpleDateFormat dd-MM-YY
     * @return the number of days between 2 dates
     */
    private int setDiffDay(String date) {
        long duration = 0;
        try {

            Date now = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date2 = sdf.parse(date);
            // 86400000 = number of millisecond in 1 day
            duration = Math.abs((now.getTime() - date2.getTime()) / 86400000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) duration;
    }

    /**
     * The type Recycle view holder.
     */
    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Item list.
         */
        public TextView itemList;
        /**
         * The Comment btn.
         */
        public ImageButton commentBtn;
        /**
         * The Recycle viewlayout.
         */
        public RelativeLayout recycleViewlayout;

        /**
         * Instantiates a new Recycle view holder.
         *
         * @param itemView the item view
         */
        public RecycleViewHolder(View itemView) {
            super(itemView);
            itemList = (TextView) itemView.findViewById(R.id.activity_history_item_list_textview);
            commentBtn = (ImageButton) itemView.findViewById(R.id.activity_history_item_list_comment_btn);
            recycleViewlayout = (RelativeLayout) itemView.findViewById(R.id.recycleView_layout);
        }
    }

}
