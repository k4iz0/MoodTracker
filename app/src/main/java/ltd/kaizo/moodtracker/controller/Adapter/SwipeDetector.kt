package ltd.kaizo.moodtracker.controller.Adapter

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * The type Swipe detector.
 * Class to detect swipe gesture
 */
class SwipeDetector
/**
 * Instantiates a new Swipe detector.
 */
(callback: Communicator) : GestureDetector.SimpleOnGestureListener() {

    private var listener: Communicator? = null

    init {
        setListener(callback)

    }

    private fun setListener(listener: Communicator) {
        this.listener = listener
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val result: Boolean?
        //start point
        val deltaX = e2.x - e1.x
        // finish point
        val deltaY = e2.y - e1.y
        if (Math.abs(deltaX) > Math.abs(deltaY)) {

            if (deltaX < 0) {
                //if we swipe to the left
                result = true
            } else {
                //we swipe on right
                result = true
            }
        } else if (deltaY < 0) {
            //swipe up
            listener!!.onSwipeUp()
            result = true
        } else {
            //swipe down
            listener!!.onSwipeDown()
            result = true
        }
        return result

    }

    interface Communicator {
        fun onSwipeUp()

        fun onSwipeDown()
    }

}
