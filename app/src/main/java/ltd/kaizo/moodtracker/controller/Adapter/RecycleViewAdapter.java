package ltd.kaizo.moodtracker.controller.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.model.MoodItem;
import ltd.kaizo.moodtracker.model.MoodList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private MoodList smileyHistory;

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
        holder.itemList.setBackgroundColor(currentItem.getMoodColor());
        holder.itemList.setText(currentItem.getComment() + " " + currentItem.getDate());
        //if there's no comment hide the button
        if (currentItem.getComment() != null) {

            holder.commentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), currentItem.getComment(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            holder.commentBtn.setVisibility(View.INVISIBLE);
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

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView itemList;
        public ImageButton commentBtn;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            itemList = (TextView) itemView.findViewById(R.id.activity_history_item_list_textview);
            commentBtn = (ImageButton) itemView.findViewById(R.id.activity_history_item_list_comment_btn);


        }
    }

}
