package ltd.kaizo.moodtracker.model


import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


/**
 * The type Mood item.
 */
class MoodItem : Serializable {

    var imageResource: Int = 0

    var index: Int = 0

    var moodColor: Int = 0

    var comment: String = ""

    var currentDate: String? = null
        private set

    /**
     * Constructor who set variable and currentDate
     * use to initialize array picturelist
     *
     * @param index         index from color array picturelist
     * @param moodColor     int resource of colors from colors.xml
     * @param imageResource int resource of the smiley picture
     */
    constructor(index: Int, moodColor: Int, imageResource: Int) {
        this.imageResource = imageResource
        this.moodColor = moodColor
        this.index = index
        setCurrentDate()
    }

    /**
     * Instantiates a new Mood item with comment
     *
     * @param index         index from color array picturelist
     * @param imageResource int resource of the smiley picture
     * @param moodColor     int resource of colors from colors.xml
     * @param comment       comment save by the comment button on mainActivity
     */
    constructor(index: Int, imageResource: Int, moodColor: Int, comment: String) {
        this.imageResource = imageResource
        this.index = index
        this.moodColor = moodColor
        this.comment = comment
        setCurrentDate()
    }

    /**
     * Sets current date.
     */
    fun setCurrentDate() {
        val now = Calendar.getInstance().time
        this.currentDate = SimpleDateFormat("dd-MM-yyyy").format(now)
    }

}
