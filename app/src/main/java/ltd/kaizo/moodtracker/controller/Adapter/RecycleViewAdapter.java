package ltd.kaizo.moodtracker.controller.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

import ltd.kaizo.moodtracker.R;
import ltd.kaizo.moodtracker.model.MoodItem;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    private ArrayList<MoodItem> mSmileyList;

    public RecycleViewAdapter(ArrayList<MoodItem> smiley) {
        mSmileyList = smiley;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        MoodItem currentItem = mSmileyList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageRessource());

    }

    @Override
    public int getItemCount() {
        return mSmileyList.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.activity_main_smiley);

        }
    }

}
