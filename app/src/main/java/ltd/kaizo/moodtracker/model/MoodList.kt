package ltd.kaizo.moodtracker.model

import java.io.Serializable
import java.util.*

/**
 * The type Mood list.
 */
class MoodList : Serializable {
    /**
     * The Mood list.
     */
    private val moodList: MutableList<MoodItem>

    val size: Int
        get() = moodList.size

    /**
     * Instantiates a new Mood list by initialize an arraylist of MoodItem
     */
    init {
        this.moodList = ArrayList()
    }

    /**
     * Add mood.
     *
     * @param mood the mood
     */
    fun addMood(mood: MoodItem) {
        this.moodList.add(mood)
    }

    /**
     * Remove mood.
     *
     * @param mood the mood
     */
    fun removeMood(mood: MoodItem) {
        this.moodList.remove(mood)
    }

    /**
     * Sets mood.
     *
     * @param index the index
     * @param mood  the mood
     */
    fun setMood(index: Int, mood: MoodItem) {
        this.moodList[index] = mood
    }

    /**
     * Gets mood list.
     *
     * @return the mood list
     */
    fun getMoodList(): List<MoodItem> {
        return moodList
    }

    /**
     * Gets mood item.
     *
     * @param index the index
     * @return the mood item
     */
    fun getMoodItem(index: Int): MoodItem {
        return moodList[index]
    }

    /**
     * Compare to by date comparator.
     *
     * @return the comparator
     */
    fun compareToByDate(): Comparator<MoodItem> {
        return Comparator { o1, o2 -> o1.currentDate!!.compareTo(o2.currentDate!!) }
    }
}
