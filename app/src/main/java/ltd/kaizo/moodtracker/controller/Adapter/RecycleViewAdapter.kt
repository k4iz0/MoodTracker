package ltd.kaizo.moodtracker.controller.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_view.view.*
import ltd.kaizo.moodtracker.R
import ltd.kaizo.moodtracker.model.MoodList
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * The type Recycle view adapter.
 */
class RecycleViewAdapter
(
        /**
         * The Smiley history.
         */
        private val smileyHistory: MoodList?) : RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>() {
    /**
     * The History limit for hte recycle list
     */
    private val HISTORY_LIMIT = 8

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return RecycleViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val currentItem = smileyHistory!!.getMoodItem(position)
        //setting up the background color
        holder.itemList.setBackgroundResource(currentItem.moodColor)
        //if there's no comment hide the button
        if (currentItem.comment.equals("", ignoreCase = true)) {
            holder.commentBtn.visibility = View.INVISIBLE

        } else {
            holder.commentBtn.setOnClickListener { v -> Toast.makeText(v.context, currentItem.comment, Toast.LENGTH_SHORT).show() }
        }
        //get the difference of day
        val diffDay = setDiffDay(currentItem.currentDate)
        var str = ""
        val context = holder.itemList.context
        when (diffDay) {
            0 ->
                //hide the mood of the day
                holder.recycleViewlayout.layoutParams = RelativeLayout.LayoutParams(0, 0)
            1 -> str = context.getString(R.string.yesterday)
            2 -> str = context.getString(R.string.before_yesterday)
            else -> str = String.format(context.getString(R.string.x_days_ago), diffDay)
        }
        holder.itemList.text = str

    }

    /**
     * limit the number of item to the limit fix by variable HISTORY_LIMIT
     *
     * @return size of recycleView list
     */
    override fun getItemCount(): Int {
        return when {
            smileyHistory == null -> 0
            smileyHistory.size > HISTORY_LIMIT -> HISTORY_LIMIT
            else -> smileyHistory.size
        }
    }

    /**
     * soustract the date of the item to the current date to return a number of days
     *
     * @param date simpleDateFormat dd-MM-YY
     * @return the number of days between 2 dates
     */
    fun setDiffDay(date: String?): Int {
        var duration: Long = 0
        try {

            val now = Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val date2 = sdf.parse(date)
            // 86400000 = number of millisecond in 1 day
            duration = Math.abs((now.time - date2.time) / 86400000)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return duration.toInt()
    }

    /**
     * The type Recycle view holder.
     */
    class RecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * The Item list.
         */
        var itemList: TextView = itemView.activity_history_item_list_textview
        /**
         * The Comment btn.
         */
        var commentBtn: ImageButton = itemView.activity_history_item_list_comment_btn
        /**
         * The Recycle viewLayout.
         */
        var recycleViewlayout: RelativeLayout = itemView.recycleView_layout

    }

}
