package ltd.kaizo.moodtracker.controller.Adapter;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * The type Swipe detector.
 * Class to detect swipe gesture
 */
public class SwipeDetector extends GestureDetector.SimpleOnGestureListener {

    private Communicator listener;

    /**
     * Instantiates a new Swipe detector.
     */
    public SwipeDetector(Communicator callback) {
        setListener(callback);

    }

    private void setListener(Communicator listener) {
        this.listener = listener;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Boolean result;
        //start point
        float deltaX = e2.getX() - e1.getX();
        // finish point
        float deltaY = e2.getY() - e1.getY();
        if (Math.abs(deltaX) > Math.abs(deltaY)) {

            if (deltaX < 0) {
                //if we swipe to the left
                result = true;
            } else {
                //we swipe on right
                result = true;
            }
        } else if (deltaY < 0) {
            //swipe up
            listener.onSwipeUp();
            result = true;
        } else {
            //swipe down
            listener.onSwipeDown();
            result = true;
        }
        return result;

    }

    public interface Communicator {
        public void onSwipeUp();

        public void onSwipeDown();
    }

}
